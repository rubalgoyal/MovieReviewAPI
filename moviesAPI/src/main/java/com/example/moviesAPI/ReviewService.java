package com.example.moviesAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate; /* Other way to talk to the database,Sometime repository just does not cut it may be you have some complex operation
       that it cant be implemented with in repository or even of you can implement it within a repository it will not be suitable.
       You can use this template to form the dynamic query and do the job inside the db without using repository */

    public Review createReview(String reviewBody, String imdbId){
        Review review = new Review(reviewBody);
        /* Now we have review we have to associate to one of the movies. Once we have review Object we need to insert it into the database*/
        reviewRepository.insert(review);
        /* Update operation will ask which class you need to update, inside matching operation we need to define matching criteria.
        * We are using the template to perform an update call on the movie class. Each movie in our collection contains an empty array of review Id
        * So we need to update this array and push a new   */

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewId").value(review));

        return review;
    }
}
