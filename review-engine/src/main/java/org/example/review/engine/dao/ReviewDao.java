package org.example.review.engine.dao;

import org.example.review.engine.models.Review;
//import org.jooq.*;
//import org.jooq.Record;
//import org.jooq.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
//import review.engine.db.Tables;
//import review.engine.db.tables.*;

import java.util.*;

@Repository
public class ReviewDao extends BaseDao {
    static final Logger logger = LoggerFactory.getLogger(String.valueOf(ReviewDao.class));

//    private DSLContext db;
//    private Table<? extends Record> table;

//    private final Table table = Tables.REVIEWS;
    private final Map<String, Review> reviews = new HashMap<>();

//    public ReviewDao(DSLContext db, Table<? extends Record> table) {
//        this.db = db;
//        this.table = table;
//    }
//
//    public Record insertNewReview(Record record) {
//        List<Field<?>> fields = Arrays.asList(
//                table.field("ID"), table.field("PRODUCT_ID"), table.field("USER_EMAIL_ID"), table.field("CREATED_AT")
//        );
//
//        List<TableField<? extends Record, ?>> values = new ArrayList<>();
//        for (Field<?> field : fields) {
//            values.add((TableField<? extends Record, ?>) field);
//        }
//
//        Record result = insertAndSelectFields(db, table, record, values);
//        if (result != null) {
//            return result.into(table);
//        } else {
//            return null;
//        }
//    }


    public Review getReview(String reviewId) {
        return reviews.get(reviewId);
    }

//    public Review addReview(Review review) {
////        logger.info(insertNewReview((Record) review).toString());
//        return reviews.put(review.getId(), review);
//    }

    public void updateReview(String reviewId, Review review) {
        reviews.put(reviewId, review);
    }
}
