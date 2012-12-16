/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.classDiagram.Member;
import data.classDiagram.Entity;
import data.classDiagram.Method;
import data.classDiagram.ClassEntity;
import data.stateDiagram.State;
import dataDraw.Constants;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public final class DataVector extends Observable {

    private static volatile DataVector singleton;
    private Entity activeEntity;
    private ClassEntity activeStateDiagram;
    private ArrayList<Entity> entityList;

    private DataVector() {
        super();
        activeEntity = null;
        activeStateDiagram = null;
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

    public double countDistance(MoveableElement entity) {
        double distance = Double.MAX_VALUE;

        if (null == activeStateDiagram) {
            for (Entity e : entityList) {
                if (entity != e) {
                    double current_distance = e.getPosition().distance(entity.getPosition());
                    if (current_distance < distance) {
                        distance = current_distance;
                    }
                }
            }
        } else {
            for (State s : activeStateDiagram.getStateDiagram().getStates()) {
                if (entity != s) {
                    double current_distance = s.getPosition().distance(entity.getPosition());
                    if (current_distance < distance) {
                        distance = current_distance;
                    }
                }
            }
        }

        return distance;
    }

    public MoveableElement getActiveElement() {
        if (activeStateDiagram != null) {
            return activeStateDiagram.getStateDiagram().getActiveState();
        }
        return activeEntity;
    }

    public Entity getActiveEntity() {
        return activeEntity;
    }

    public void setActiveElement(int x, int y) {
        if (activeStateDiagram != null) {
            activeStateDiagram.getStateDiagram().setActiveState(x, y);
        } else {
            setActiveEntity(x, y);
        }
    }

    public void setActiveEntity(int x, int y) {
        if (activeEntity != null && activeEntity.getPosition().isIn(x, y)) {
            return;
        }

        activeEntity = findEntity(x, y);

        refresh();
    }

    public ClassEntity getActiveStateDiagram() {
        return activeStateDiagram;
    }

    public void setActiveStateDiagram(String name) {
        activeStateDiagram = null;

        for (Entity entity : DataVector.getInstance().getEntityList()) {
            if (entity instanceof ClassEntity) {
                if (entity.getName().equals(name)) {
                    activeStateDiagram = (ClassEntity) entity;
                }
            }
        }

        refresh();
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
        refresh();
    }

    public void add(Entity entity) {
        entityList.add(entity);
        refresh();
    }

    public void addMethodToActiveEntity(Method m) {
        Entity e = getActiveEntity();
        if (e != null) {
            e.addMethod(m);
            refresh();
        }
    }

    public void addMemberToActiveEntity(Member m) {
        Entity e = getActiveEntity();
        if (e != null) {
            e.addMember(m);
            refresh();
        }
    }

    public void refresh() {
        setChanged();
        notifyObservers();
    }

    public void clear() {
        entityList.clear();
        refresh();
    }
}
