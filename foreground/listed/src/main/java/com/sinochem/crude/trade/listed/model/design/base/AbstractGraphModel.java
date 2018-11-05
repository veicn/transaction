package com.sinochem.crude.trade.listed.model.design.base;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 图的基类
 * @author Yichen Zhao
 * date: 20180206
 */
public abstract class AbstractGraphModel {

    protected HashMap<VertexModel, Set<VertexModel>> vertexMap;

    public AbstractGraphModel() {
        this.vertexMap = new HashMap<>();
    }

    public abstract void initializeGrpah();

    public void addVertex(VertexModel vertex, VertexModel... adjacentVertexs) {
        Set<VertexModel> adjacentVertexSet = new HashSet<>();

        for (VertexModel adjacentVertex : adjacentVertexs) {
            adjacentVertexSet.add(adjacentVertex);
        }

        vertexMap.put(vertex, adjacentVertexSet);
    }

    /**
     * 重置后，将所有点的状态设置为“未访问”
     */
    public void resetVertexes() {
        Iterator<VertexModel> keyIterator = vertexMap.keySet().iterator();

        while (keyIterator.hasNext()) {
            keyIterator.next().setVisited(false);
        }
    }

    public Set<VertexModel> getAdjacentVertexSet(VertexModel vertexModel) {
        return this.vertexMap.get(vertexModel);
    }

    /** getters and setters */
    public HashMap<VertexModel, Set<VertexModel>> getVertexMap() {
        return vertexMap;
    }

    public void setVertexMap(HashMap<VertexModel, Set<VertexModel>> vertexMap) {
        this.vertexMap = vertexMap;
    }
}
