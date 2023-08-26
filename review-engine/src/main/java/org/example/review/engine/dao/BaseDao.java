package org.example.review.engine.dao;

//import org.jooq.Condition;
//import org.jooq.DSLContext;
//import org.jooq.Record;
//import org.jooq.SelectConditionStep;
//import org.jooq.Table;
//import org.jooq.TableField;
//
//import java.time.LocalDateTime;
//import java.util.Collection;
//import java.util.List;

//import static org.jooq.impl.DSL.noCondition;

/**
 * Abstract class for DAOs to house reusable db methods.
 */
public abstract class BaseDao {
//
//    public Record insertAndSelectFields(
//            DSLContext db,
//            Table<? extends Record> table,
//            Record record,
//            Collection<? extends TableField<? extends Record, ?>> values) {
//        return db.insertInto(table)
//                .set(record)
//                .onConflictDoNothing()
//                .returningResult(values)
//                .fetchOne();
//    }
//
//    public Record insertAndSelectAll(
//            DSLContext db,
//            Table<? extends Record> table,
//            Record record) {
//        return db.insertInto(table)
//                .set(record)
//                .onConflictDoNothing()
//                .returning()
//                .fetchOne();
//    }
//
//    public Record getRecordWhere(
//            DSLContext db,
//            Table<? extends Record> table,
//            Condition condition) {
//        return fetchRecordsWhere(db, table, condition).fetchOne();
//    }
//
//    public Record getRecordOrNullWhere(
//            DSLContext db,
//            Table<? extends Record> table,
//            Condition condition) {
//        return fetchRecordsWhere(db, table, condition).fetchAny();
//    }
//
//    public SelectConditionStep<Record> fetchRecordsWhere(
//            DSLContext db,
//            Table<? extends Record> table,
//            Condition condition) {
//        return (SelectConditionStep<Record>) db.selectFrom(table)
//                .where(condition);
//    }
//
//    public List<Record> joinTwoTables(
//            DSLContext db,
//            Table<? extends Record> table1,
//            Table<? extends Record> table2,
//            TableField<? extends Record, String> col1,
//            TableField<? extends Record, String> col2,
//            Condition condition,
//            TableField<? extends Record, LocalDateTime> order,
//            long offset,
//            long limit) {
//        return db.select()
//                .from(table1)
//                .join(table2).on(col1.eq(col2))
//                .where(condition)
//                .orderBy(order.desc())
//                .limit(offset, limit)
//                .fetch();
//    }
//
//    public List<Record> leftJoinTwoTables(
//            DSLContext db,
//            Table<? extends Record> table1,
//            Table<? extends Record> table2,
//            Condition onCondition,
//            Condition whereCondition,
//            TableField<? extends Record, LocalDateTime> order,
//            long offset,
//            long limit) {
//        return db.selectDistinct()
//                .from(table1)
//                .leftJoin(table2).on(onCondition)
//                .where(whereCondition)
//                .orderBy(order.desc())
//                .limit(offset, limit)
//                .fetch();
//    }
//
//    public Record joinTwoTablesForRecord(
//            DSLContext db,
//            Table<? extends Record> table1,
//            Table<? extends Record> table2,
//            TableField<? extends Record, String> col1,
//            TableField<? extends Record, String> col2,
//            Condition condition,
//            TableField<? extends Record, LocalDateTime> order) {
//        return db.select()
//                .from(table1)
//                .join(table2).on(col1.eq(col2))
//                .where(condition)
//                .orderBy(order.desc())
//                .fetchOne();
//    }
}

