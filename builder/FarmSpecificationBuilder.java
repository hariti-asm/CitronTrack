package ma.hariti.asmaa.wrm.citrontrack.builder;
import jakarta.persistence.criteria.Predicate;
import ma.hariti.asmaa.wrm.citrontrack.entity.Farm;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FarmSpecificationBuilder {
    private final List<Specification<Farm>> specifications = new ArrayList<>();

    public FarmSpecificationBuilder name(String name) {
        if (name != null && !name.isEmpty()) {
            specifications.add((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%")
            );
        }
        return this;
    }

    public FarmSpecificationBuilder location(String location) {
        if (location != null && !location.isEmpty()) {
            specifications.add((root, query, cb) ->
                    cb.like(cb.lower(root.get("location")), "%" + location.toLowerCase() + "%")
            );
        }
        return this;
    }

    public FarmSpecificationBuilder minArea(Double minArea) {
        if (minArea != null) {
            specifications.add((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("totalArea"), minArea)
            );
        }
        return this;
    }

    public FarmSpecificationBuilder maxArea(Double maxArea) {
        if (maxArea != null) {
            specifications.add((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("totalArea"), maxArea)
            );
        }
        return this;
    }

    public FarmSpecificationBuilder createdAfter(LocalDate startDate) {
        if (startDate != null) {
            specifications.add((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("creationDate"), startDate)
            );
        }
        return this;
    }

    public FarmSpecificationBuilder createdBefore(LocalDate endDate) {
        if (endDate != null) {
            specifications.add((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("creationDate"), endDate)
            );
        }
        return this;
    }

    public FarmSpecificationBuilder withMinFields(Integer minFields) {
        if (minFields != null) {
            specifications.add((root, query, cb) ->
                    cb.greaterThanOrEqualTo(cb.size(root.get("fields")), minFields)
            );
        }
        return this;
    }

    public Specification<Farm> build() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (Specification<Farm> spec : specifications) {
                Predicate predicate = spec.toPredicate(root, query, cb);
                if (predicate != null) {
                    predicates.add(predicate);
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }}