package com.bank.example.dao;

import com.bank.example.dto.OperatorRatingDto;
import com.bank.example.model.OperatorRating;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
import org.hibernate.type.*;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OperatorRatingDaoImpl extends AbstractDao<Long, OperatorRating> implements OperatorRatingDao {

    @Override
    public List<OperatorRatingDto> getOperatingRaitingDto() {
      List<Tuple> tuples =  entityManager.createNativeQuery("SELECT AVG(r.rating), o.first_name, o.last_name, o.id  FROM operator_rating r JOIN operator o on o.id = r.operator_id " +
              "GROUP BY o.id ORDER BY AVG(r.rating) DESC LIMIT 10", Tuple.class).getResultList();

      List<OperatorRatingDto> operatorRatings = new ArrayList<>();
      for (Tuple tuple : tuples) {
        OperatorRatingDto dto = OperatorRatingDto.builder()
                  .averageRating(tuple.get("avg", BigDecimal.class).doubleValue())
                  .firstName(tuple.get("first_name", String.class))
                  .lastName(tuple.get("last_name",String.class))
                  .id(tuple.get("id", BigInteger.class).longValue()).build();

          operatorRatings.add(dto);
      }
      return operatorRatings;
    }
}
