-- Procedure that sets the updated_at column
CREATE OR REPLACE FUNCTION update_updated_at() RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at = current_timestamp;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Procedure that sets the created_at column
CREATE OR REPLACE FUNCTION insert_created_at() RETURNS TRIGGER AS
$$
BEGIN
    NEW.created_at = current_timestamp;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-- Update trigger to all tables
DO
$$
-- t holds a table name
    DECLARE
        t text;
    BEGIN
        FOR t IN
            -- All tables that are in the 'app' schema in the target database (pods) and not belonging to flyway
            SELECT table_name
            FROM information_schema.tables
            WHERE table_schema = 'craft'
              AND table_name NOT LIKE '%schema_history'
            LOOP
                -- Add the updated_at column, 0 default for existing rows, to identify as pre-existing
                EXECUTE format('ALTER TABLE %s
                ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP DEFAULT to_timestamp(0);', t);
                -- Resetting the default to be current time for new rows.
                EXECUTE format('ALTER TABLE %s
                ALTER COLUMN updated_at SET DEFAULT current_timestamp;', t);
                -- Drop the update trigger if it exists. Wasteful, but simpler than checking for one
                EXECUTE format('DROP TRIGGER IF EXISTS update_updated_at ON %s', t);
                -- Create a trigger for the table
                EXECUTE format('CREATE TRIGGER update_updated_at
                        BEFORE UPDATE ON %I
                        FOR EACH ROW EXECUTE PROCEDURE update_updated_at()', t);

                -- Add the created_at column, 0 default for existing rows, to identify as pre-existing
                EXECUTE format('ALTER TABLE %s
                ADD COLUMN IF NOT EXISTS created_at TIMESTAMP DEFAULT to_timestamp(0);', t);
                -- Resetting the default to be current time for new rows.
                EXECUTE format('ALTER TABLE %s
                ALTER COLUMN created_at SET DEFAULT current_timestamp;', t);
                -- Drop the insert trigger if it exists. Wasteful, but simpler than checking for one
                EXECUTE format('DROP TRIGGER IF EXISTS insert_created_at ON %s', t);
                -- Create a trigger for the table
                EXECUTE format('CREATE TRIGGER insert_created_at
                        BEFORE INSERT ON %I
                        FOR EACH ROW EXECUTE PROCEDURE insert_created_at()', t);
            END LOOP;
    END;
$$ LANGUAGE plpgsql;