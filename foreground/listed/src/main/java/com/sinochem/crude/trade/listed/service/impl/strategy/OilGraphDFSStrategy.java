package com.sinochem.crude.trade.listed.service.impl.strategy;

import com.sinochem.crude.trade.listed.model.design.OilGraphModel;
import com.sinochem.crude.trade.listed.model.design.OilVertexModel;
import com.sinochem.crude.trade.listed.model.design.base.AbstractGraphModel;
import com.sinochem.crude.trade.listed.model.design.base.VertexModel;
import com.sinochem.crude.trade.listed.service.impl.strategy.base.AbstractGraphTraverseStrategy;

import java.util.*;

/**
 * 搜索不同油种的转价路径的广度优先遍历策略
 * 线程不安全
 * @author Yichen Zhao
 * date: 20180202
 */
public class OilGraphDFSStrategy implements AbstractGraphTraverseStrategy {

    private Map<VertexModel, VertexModel> path;

    public OilGraphDFSStrategy() {
        this.path = new HashMap<>();
    }

    @Override
    public void traverse(AbstractGraphModel graphModel, VertexModel startVertex) {
        if (!(graphModel instanceof OilGraphModel) || !(startVertex instanceof OilVertexModel)) {
            return;
        }

        OilGraphModel graph = (OilGraphModel) graphModel;
        if (!graph.containsVertex((OilVertexModel) startVertex)) {
            return;
        }

        // 检查当前月份是否和图中月份吻合
        Calendar calendar = Calendar.getInstance();
        Calendar graphCalendar = graph.getCalendar();
        if (calendar.get(Calendar.YEAR) != graphCalendar.get(Calendar.YEAR)
                || calendar.get(Calendar.MONTH) != graphCalendar.get(Calendar.MONTH)) {
            graph.initializeGrpah();
        }

        VertexModel startVertexModel = graph.getVertexInGraph(startVertex);
        Queue<VertexModel> vertexQueue = new LinkedList<>();
        startVertexModel.setVisited(true);
        vertexQueue.add(startVertexModel);

        while (!vertexQueue.isEmpty()) {
            OilVertexModel first = (OilVertexModel) vertexQueue.poll();

            /* 类型转换 */
            Set<VertexModel> adjacentVertexes = graph.getAdjacentVertexSet(first);

            if (adjacentVertexes != null) {
                Set<OilVertexModel> adjacentVertexSet = new HashSet<>();
                for (VertexModel vertexModel : adjacentVertexes) {
                    adjacentVertexSet.add((OilVertexModel) vertexModel);
                }

                /* 按照油种类型转换为Queue以满足先转产品的需求 */
                Queue<OilVertexModel> adjacentVertexQueue = graph.convertToQueueByOilType(first, adjacentVertexSet);

                for (VertexModel adjacentVertex : adjacentVertexQueue) {
                    if (!adjacentVertex.isVisited()) {
                        adjacentVertex.setVisited(true);
                        path.put(adjacentVertex, first);
                        vertexQueue.add(adjacentVertex);
                    }
                }
            }
        }
    }

    @Override
    public Queue<VertexModel> getShortestPath(AbstractGraphModel graphModel, VertexModel startVertexModel, VertexModel endVertexModel) {
        OilGraphModel graph = (OilGraphModel) graphModel;
        OilVertexModel startVertex = (OilVertexModel) startVertexModel;
        OilVertexModel endVertex = (OilVertexModel) endVertexModel;

        Queue shortestPath = new LinkedList();

        if ((!graph.containsVertex(startVertex) && graph.isEntry(endVertex))
                || (graph.isEntry(startVertex) && !graph.containsVertex(endVertex))
                || (graph.isEntry(startVertex) && graph.isEntry(endVertex))) {
            shortestPath.add(startVertex);
            shortestPath.add(endVertex);

            return shortestPath;
        }

        if (!graph.containsVertex(startVertex) && !graph.containsVertex(endVertex)) {
            shortestPath.add(startVertex);
            shortestPath.add(endVertex);
        } else if (graph.containsVertex(startVertex) && graph.containsVertex(endVertex)) {
            traverse(graph, startVertex);

            Stack<VertexModel> pathStack = new Stack<>();
            getShortestPath(endVertex, pathStack);

            while (!pathStack.isEmpty()) {
                VertexModel vertexModel = pathStack.pop();
                shortestPath.add(vertexModel);
            }

            graph.resetVertexes();
            this.path.clear();
        } else {
            OilVertexModel entryVertex = graph.getClosestEntry(startVertex);

            Queue<VertexModel> subPath1 = getShortestPath(graph, startVertex, entryVertex);
            for (VertexModel temp : subPath1) {
                if (!containsVertex(shortestPath, temp)) {
                    shortestPath.add(temp);
                }
            }

            Queue<VertexModel> subPath2 = getShortestPath(graph, entryVertex, endVertex);
            for (VertexModel temp : subPath2) {
                if (!containsVertex(shortestPath, temp)) {
                    shortestPath.add(temp);
                }
            }
        }

        return shortestPath;
    }

    private void getShortestPath(VertexModel endVertex, Stack<VertexModel> pathStack) {
        pathStack.push(endVertex);

        if (path.containsKey(endVertex)) {
            VertexModel trace = path.get(endVertex);
            path.remove(endVertex);
            getShortestPath(trace, pathStack);
        }
    }

    private boolean containsVertex(Queue<VertexModel> vertexQueue, VertexModel vertex) {
        for (VertexModel temp : vertexQueue) {
            if (temp.equals(vertex)) {
                return true;
            }
        }

        return false;
    }
}
