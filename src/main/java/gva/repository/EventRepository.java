package gva.repository;

import gva.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByTimeStampIsAfter(LocalDateTime ts);

    Long countByTimeStampIsAfter(LocalDateTime ts);
}
