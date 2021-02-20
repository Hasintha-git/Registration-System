package dao;

import entity.Student;
import entity.SuperEntity;

import java.util.List;

public interface SuperDao<Entity extends SuperEntity,Id> {
    public boolean add(Entity entity)throws Exception;
    public boolean update(Entity entity)throws Exception;
    public boolean delete(Id id)throws Exception;
    public Entity find(Id id)throws Exception;
    public List<Entity> loadAll()throws Exception;

}
