/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import dataDraw.Constants;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public final class DataVector extends Observable {

    private static volatile DataVector singleton;
    private Entity activeEntity;
    private ArrayList<Entity> entityList;
    private DataVector() {
        super();
        activeEntity = null;
        entityList = new ArrayList<Entity>();
    }

    public static DataVector getInstance() {
        if (singleton == null) {
            synchronized (DataVector.class) {
                if (singleton == null) {
                    singleton = new DataVector();
                }
            }
        }
        return singleton;
    }

    public ArrayList<Entity> getEntityList() {
        return entityList;
    }

    public Entity findEntity(String name) {
        for (Entity e : entityList) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    public Entity findEntity(int x, int y) {
        for (Entity e : entityList) {
            if (e.getPosition().isIn(x, y)) {
                return e;
            }
        }
        return null;
    }

    public double countDistance(Entity entity) {
        double distance = Double.MAX_VALUE;

        for (Entity e : entityList) {
            if (entity != e) {
                double current_distance = e.getPosition().distance(entity.getPosition());
                if (current_distance < distance) {
                    distance = current_distance;
                }
            }
        }

        return distance;
    }

    public Entity getActiveEntity() {
        return activeEntity;
    }

    public void setActiveEntity(int x, int y) {
        if (activeEntity != null && activeEntity.getPosition().isIn(x, y)) {
            return;
        }

        activeEntity = findEntity(x, y);

        setChanged();
        notifyObservers();
    }

    public void addNewEntity(Entity entity) {
        Position pos = entity.getPosition();

        pos.setHeight(Constants.MIN_DISTANCE);
        pos.setWidth(Constants.MIN_DISTANCE);

        pos.setX(1);
        pos.setY(1);

        Random r = new Random();

        while (countDistance(entity) < Constants.MIN_DISTANCE) {
            pos.setX(r.nextInt(Constants.X_RANDOM));
            pos.setY(r.nextInt(Constants.Y_RANDOM));
        }

        entityList.add(entity);
        setChanged();
        notifyObservers();
    }

    public void add(Entity entity) {
        entityList.add(entity);
        setChanged();
        notifyObservers();
    }
    public void repaint(){
        setChanged();
        notifyObservers();
    }
}
