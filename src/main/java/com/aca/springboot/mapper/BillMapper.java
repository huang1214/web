package com.aca.springboot.mapper;

import com.aca.springboot.entities.Bill;
import com.aca.springboot.entities.BillMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BillMapper {
    /**
     * 查询所有账单信息
     * @return 账单信息列表
     */
    public List<Bill> findAllBill();

    /**
     * 根据账单状态查询
     * @param state 账单状态
     * @return 账单列表
     */
    public List<Bill> findBillByState(@Param("state") String state);

    /**
     * 根据竞赛编号，参赛年费，团队负责人查询
     * @param ctid 竞赛编号
     * @param startOfYear 参赛年份那一年的第一天，（这里只传参赛那一年的一月一号，如：2012-01-01）
     * @param endOfYear 参赛年份那一年的最后一天，（这里只传参赛那一年的12月31号，如：2012-12-31）
     * @param groupleader 项目负责人id
     * @return 一条账单数据
     */
    public Bill selectBillByCtidCyearGroupleader(@Param("ctid") String ctid,
                                                 @Param("startOfYear") String startOfYear,
                                                 @Param("endOfYear") String endOfYear,
                                                 @Param("groupleader") String groupleader);

    /**
     * 添加账单信息
     * @param bill 账单信息的映射
     * @return  执行成功的条数，1为成功
     */
    public int addBill(Bill bill);

    public void deleteBill();

    /**
     * 插入账单对应表
     */
    public int addBillMember(BillMember billMember);


}
