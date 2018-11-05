package com.sinochem.crude.trade.listed.service.impl.strategy.base;

import com.sinochem.crude.trade.listed.model.design.base.AbstractGraphModel;
import com.sinochem.crude.trade.listed.model.design.base.VertexModel;

import java.util.Queue;

/**
 * 图遍历策略
 * @author Yichen Zhao
 * date: 20180202
 */
public interface AbstractGraphTraverseStrategy {

    void traverse(AbstractGraphModel graph, VertexModel startVertex);

    Queue<VertexModel> getShortestPath(AbstractGraphModel graph, VertexModel startVertex, VertexModel endVertex);
}
