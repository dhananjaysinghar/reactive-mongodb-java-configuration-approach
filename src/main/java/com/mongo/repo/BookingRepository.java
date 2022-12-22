package com.mongo.repo;

import com.mongo.model.BookingRequest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends ReactiveMongoRepository<BookingRequest, String> {

}
