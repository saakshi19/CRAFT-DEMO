CREATE TABLE "reviews"(
    "id" BIGSERIAL PRIMARY KEY,
    "product_id" BIGINT NOT NULL,
    "user_email_id" VARCHAR NOT NULL,
    "rating" float8 NOT NULL,
    "category_rating" VARCHAR,
    "metadata" VARCHAR,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "deleted_at" timestamp
);
