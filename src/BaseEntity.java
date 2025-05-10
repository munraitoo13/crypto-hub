import java.util.Date;
import java.util.UUID;

public abstract class BaseEntity {
    private final UUID id;
    private final Date createdAt;
    private Date updatedAt;

    public BaseEntity() {
        this.id = UUID.randomUUID();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public UUID getId() { return id; }

    public Date getCreatedAt() { return createdAt; }

    public Date getUpdatedAt() { return updatedAt; }

    // correcao updateAt,

    public void setUpdatedAtNow() {
        this.updatedAt = new Date();
    }
}
