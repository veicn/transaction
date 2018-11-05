package com.sinochem.crude.trade.listed.model.design;

import com.sinochem.crude.trade.listed.enums.OilModelTypeEnum;
import com.sinochem.crude.trade.listed.model.design.base.AbstractGraphModel;
import com.sinochem.crude.trade.listed.model.design.base.VertexModel;
import com.sinochem.crude.trade.listed.service.impl.factory.OilModelFactory;

import java.util.*;

/**
 * 组合相关计价策略，实现点价功能，根据业务，按照以下无向图进行组合
 * 以二月份为例，若当前时间不是二月而是三月，则除一，二行连接的竖线外，其它竖线向右侧推1，以此类推
 *
 *  WTI1 —— WTI2 —— WTI3 —— WTI4 —— WTI5 —— WTI6 —— WTI7 —— WTI8 —— WTI9 —— WTI10 —— WTI11 —— WTI12
 *    |       |       |       |       |       |       |       |       |       |       |       |
 *  ICE1 —— ICE2 —— ICE3 —— ICE4 —— ICE5 —— ICE6 —— ICE7 —— ICE8 —— ICE9 —— ICE10 —— ICE11 —— ICE12
 *                            |       |
 * DUBAI1——DUBAI2——DUBAI3——DUBAI4——DUBAI5——DUBAI6——DUBAI7——DUBAI8——DUBAI9——DUBAI10——DUBAI11——DUBAI12
 *                    |       |       |
 *  DTD1 —— DTD2 —— DTD3 —— DTD4 —— DTD5 —— DTD6 —— DTD7 —— DTD8 —— DTD9 —— DTD10 —— DTD11 —— DTD12
 *
 *  根据业务需求，以二月份为例，目前节点为ICE4，ICE5，DUBAI3至DUBAI6，DTD3至DTD6，共10个点，即实际的图为：
 *
 *                          ICE4    ICE5
 *                            |       |
 *                 DUBAI3——DUBAI4——DUBAI5——DUBAI6
 *                    |       |       |
 *                  DTD3 —— DTD4 —— DTD5 —— DTD6
 *
 *  矩阵较为稀疏，因此采用邻接列表方式实现
 *
 *  @author Yichen Zhao
 *  date: 20180201
 */
public class OilGraphModel extends AbstractGraphModel {

    /**
     * 对于出现油种不同的路径，赋予权重为0.5，正常路径为1，最后得出最小值
     */
    private final Float TYPE_WEIGHT = Float.valueOf(0.5f);
    private final Float UNIT_DISTANCE = Float.valueOf(1);

    private Calendar calendar;

    /**
     * 图的入口点，为ICE(month + 2)和ICE(month + 3)
     */
    private Set<OilVertexModel> entrySet;

    public OilGraphModel() {
        super();
        this.entrySet = new HashSet<>();

        this.initializeGrpah();
    }

    @Override
    public void initializeGrpah() {
        this.vertexMap.clear();
        this.entrySet.clear();

        Calendar calendar = Calendar.getInstance();
        this.calendar = calendar;

        Calendar tempCalendar;

        //ICE(month + 2)
        tempCalendar = (Calendar) calendar.clone();
        tempCalendar.add(Calendar.MONTH, 2);
        OilVertexModel iceBrentModelMonthPlus2Vertex = new OilVertexModel(
                OilModelFactory.getOilModel(
                        OilModelTypeEnum.ICE_BRENT.getCode(),
                        tempCalendar.get(Calendar.YEAR),
                        tempCalendar.get(Calendar.MONTH) + 1
                )
        );

        //ICE(month + 3)
        tempCalendar = (Calendar) calendar.clone();
        tempCalendar.add(Calendar.MONTH, 3);
        OilVertexModel iceBrentModelMonthPlus3Vertex = new OilVertexModel(
                OilModelFactory.getOilModel(
                        OilModelTypeEnum.ICE_BRENT.getCode(),
                        tempCalendar.get(Calendar.YEAR),
                        tempCalendar.get(Calendar.MONTH) + 1
                )
        );

        //DUBAI(month + 1)
        tempCalendar = (Calendar) calendar.clone();
        tempCalendar.add(Calendar.MONTH, 1);
        OilVertexModel dubaiModelPlus1Vertex = new OilVertexModel(
                OilModelFactory.getOilModel(
                        OilModelTypeEnum.DUBAI.getCode(),
                        tempCalendar.get(Calendar.YEAR),
                        tempCalendar.get(Calendar.MONTH) + 1
                )
        );

        //DUBAI(month + 2)
        tempCalendar = (Calendar) calendar.clone();
        tempCalendar.add(Calendar.MONTH, 2);
        OilVertexModel dubaiModelPlus2Vertex = new OilVertexModel(
                OilModelFactory.getOilModel(
                        OilModelTypeEnum.DUBAI.getCode(),
                        tempCalendar.get(Calendar.YEAR),
                        tempCalendar.get(Calendar.MONTH) + 1
                )
        );

        //Dubai(month + 3)
        tempCalendar = (Calendar) calendar.clone();
        tempCalendar.add(Calendar.MONTH, 3);
        OilVertexModel dubaiModelPlus3Vertex = new OilVertexModel(
                OilModelFactory.getOilModel(
                        OilModelTypeEnum.DUBAI.getCode(),
                        tempCalendar.get(Calendar.YEAR),
                        tempCalendar.get(Calendar.MONTH) + 1
                )
        );

        //Dubai(month + 4)
        tempCalendar = (Calendar) calendar.clone();
        tempCalendar.add(Calendar.MONTH, 4);
        OilVertexModel dubaiModelPlus4Vertex = new OilVertexModel(
                OilModelFactory.getOilModel(
                        OilModelTypeEnum.DUBAI.getCode(),
                        tempCalendar.get(Calendar.YEAR),
                        tempCalendar.get(Calendar.MONTH) + 1
                )
        );

        //DTD(month + 1)
        tempCalendar = (Calendar) calendar.clone();
        tempCalendar.add(Calendar.MONTH, 1);
        OilVertexModel dtdBrentModelPlus1Vertex = new OilVertexModel(
                OilModelFactory.getOilModel(
                        OilModelTypeEnum.DTD_BRENT.getCode(),
                        tempCalendar.get(Calendar.YEAR),
                        tempCalendar.get(Calendar.MONTH) + 1
                )
        );

        //DTD(month + 2)
        tempCalendar = (Calendar) calendar.clone();
        tempCalendar.add(Calendar.MONTH, 2);
        OilVertexModel dtdBrentModelPlus2Vertex = new OilVertexModel(
                OilModelFactory.getOilModel(
                        OilModelTypeEnum.DTD_BRENT.getCode(),
                        tempCalendar.get(Calendar.YEAR),
                        tempCalendar.get(Calendar.MONTH) + 1
                )
        );

        //DTD(month + 3)
        tempCalendar = (Calendar) calendar.clone();
        tempCalendar.add(Calendar.MONTH, 3);
        OilVertexModel dtdBrentModelPlus3Vertex = new OilVertexModel(
                OilModelFactory.getOilModel(
                        OilModelTypeEnum.DTD_BRENT.getCode(),
                        tempCalendar.get(Calendar.YEAR),
                        tempCalendar.get(Calendar.MONTH) + 1
                )
        );

        //DTD(month + 4)
        tempCalendar = (Calendar) calendar.clone();
        tempCalendar.add(Calendar.MONTH, 4);
        OilVertexModel dtdBrentModelPlus4Vertex = new OilVertexModel(
                OilModelFactory.getOilModel(
                        OilModelTypeEnum.DTD_BRENT.getCode(),
                        tempCalendar.get(Calendar.YEAR),
                        tempCalendar.get(Calendar.MONTH) + 1
                )
        );

        /** 构建邻接表 */
        this.addVertex(iceBrentModelMonthPlus2Vertex,
                dubaiModelPlus2Vertex);

        this.addVertex(iceBrentModelMonthPlus3Vertex,
                dubaiModelPlus3Vertex);

        this.addVertex(dubaiModelPlus1Vertex,
                dubaiModelPlus2Vertex, dtdBrentModelPlus1Vertex);

        this.addVertex(dubaiModelPlus2Vertex,
                iceBrentModelMonthPlus2Vertex, dubaiModelPlus1Vertex, dubaiModelPlus3Vertex, dtdBrentModelPlus2Vertex);

        this.addVertex(dubaiModelPlus3Vertex,
                iceBrentModelMonthPlus3Vertex, dubaiModelPlus2Vertex, dubaiModelPlus4Vertex, dtdBrentModelPlus3Vertex);

        this.addVertex(dubaiModelPlus4Vertex,
                dubaiModelPlus3Vertex);

        this.addVertex(dtdBrentModelPlus1Vertex,
                dubaiModelPlus1Vertex, dtdBrentModelPlus2Vertex);

        this.addVertex(dtdBrentModelPlus2Vertex,
                dubaiModelPlus2Vertex, dtdBrentModelPlus1Vertex, dtdBrentModelPlus3Vertex);

        this.addVertex(dtdBrentModelPlus3Vertex,
                dubaiModelPlus3Vertex, dtdBrentModelPlus2Vertex, dtdBrentModelPlus4Vertex);

        this.addVertex(dtdBrentModelPlus4Vertex,
                dtdBrentModelPlus3Vertex);

        /** 设置入口点 */
        this.entrySet.add(iceBrentModelMonthPlus2Vertex);
        this.entrySet.add(iceBrentModelMonthPlus3Vertex);
    }

    /**
     * 根据油种不同，将与起点不同的油种优先排列，实现优先转油种的需求
     */
    public Queue<OilVertexModel> convertToQueueByOilType(OilVertexModel first, Set<OilVertexModel> vertexModelSet) {
        HashMap<Float, List<OilVertexModel>> vertexModelListMap = new HashMap<>();
        vertexModelListMap.put(UNIT_DISTANCE, new LinkedList<OilVertexModel>());
        vertexModelListMap.put(TYPE_WEIGHT, new LinkedList<OilVertexModel>());

        Iterator<OilVertexModel> setIterator = vertexModelSet.iterator();
        while (setIterator.hasNext()) {
            OilVertexModel vertexModel = setIterator.next();

            if (vertexModel.getOilModel().sameType(first.getOilModel())) {
                vertexModelListMap.get(UNIT_DISTANCE).add(vertexModel);
            } else {
                vertexModelListMap.get(TYPE_WEIGHT).add(vertexModel);
            }
        }

        Queue<OilVertexModel> vertexModelQueue = new LinkedList<>();

        vertexModelQueue.addAll(vertexModelListMap.get(TYPE_WEIGHT));
        vertexModelQueue.addAll(vertexModelListMap.get(UNIT_DISTANCE));

        return vertexModelQueue;
    }

    public OilVertexModel getClosestEntry(OilVertexModel oilVertexModel) {
        long timeInMillis = oilVertexModel.getOilModel().getCalendar().getTimeInMillis();

        long minValue = 0L;
        OilVertexModel entryResult = null;
        for (OilVertexModel entry : entrySet) {
            long difference = Math.abs(entry.getOilModel().getCalendar().getTimeInMillis() - timeInMillis);

            if (entryResult == null || difference < minValue) {
                entryResult = entry;
                minValue = difference;
            }
        }

        return entryResult;
    }

    public VertexModel getVertexInGraph(VertexModel oilVertexModel) {
        if (!vertexMap.containsKey(oilVertexModel)) {
            return null;
        }

        Set<VertexModel> keySet = vertexMap.keySet();
        for (VertexModel vertexModel : keySet) {
            if (vertexModel.equals(oilVertexModel)) {
                return vertexModel;
            }
        }

        return null;
    }

    public boolean containsVertex(OilVertexModel vertexModel) {
        return this.vertexMap.containsKey(vertexModel);
    }

    public boolean isEntry(OilVertexModel vertexModel) {
        return this.entrySet.contains(vertexModel);
    }

    /** getters and setters */
    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Set<OilVertexModel> getEntrySet() {
        return entrySet;
    }

    public void setEntrySet(Set<OilVertexModel> entrySet) {
        this.entrySet = entrySet;
    }
}
