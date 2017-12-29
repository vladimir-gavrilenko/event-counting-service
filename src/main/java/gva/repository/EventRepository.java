package gva.repository;

import gva.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional // readOnly = false
public interface EventRepository extends JpaRepository<Event, Long> {
    @Transactional(readOnly = true)
    List<Event> findByTimeStampIsBetween(LocalDateTime from, LocalDateTime to);

    @Transactional(readOnly = true)
    Long countByTimeStampIsBetween(LocalDateTime from, LocalDateTime to);
}
