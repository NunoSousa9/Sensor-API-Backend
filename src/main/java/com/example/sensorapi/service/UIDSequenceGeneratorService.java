package com.example.sensorapi.service;

import com.example.sensorapi.model.DatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import java.util.Objects;

@Service
public class UIDSequenceGeneratorService {

    @Autowired
    private MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public void resetSequence(String seqName) {
        mongoOperations.findAndModify(
                new Query(Criteria.where("_id").is(seqName)),
                new Update().set("seq", 1),
                DatabaseSequence.class
        );
    }

    public long getCurrentSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findOne(query(where("_id").is(seqName)),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 0;
    }
}
