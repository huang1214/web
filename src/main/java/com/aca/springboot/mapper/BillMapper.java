package com.aca.springboot.mapper;

import com.aca.springboot.entities.Bill;
import com.aca.springboot.entities.BillMember;
import com.aca.springboot.vo.BillVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     * @param cyear 参赛年份那一年
     * @param groupleader 项目负责人id
     * @return 一条账单数据
     */
    public Bill selectBillByCtidCyearGroupleader(@Param("ctid") String ctid,
                                                 @Param("cyear") String cyear,
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

    //获取报销列表
    public List<BillVO> get_bill_list(Map map);

    //管理员获取全部的报销列表
    public List<BillVO> get_all_bill_list();

    //修改状态
    public int change_bill_state(Map map);

    //删除报销表
    public int deleteBill(Map map);

    //删除报销对应表
    public int deleteBillMember(Map map);

    //获取仪表盘信息
    public Integer getBillCount(Map map);

    //获取仪表盘信息,管理员
    public Integer getBillCountAdmin(Map map);

}
