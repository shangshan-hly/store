package com.cy.store.service;

import com.cy.store.vo.CartVO;

import java.util.List;

/** 处理商品数据的业务层接口 */
public interface ICartService {
    void addToCart(Integer uid, Integer pid, Integer amount, String username);
    void reduceToCart(Integer cid, Integer uid, String username);
    List<CartVO> getVOByUid(Integer uid);
    Integer addNum(Integer cid, Integer uid, String username);
    Integer reduceNum(Integer cid, Integer uid, String username);
    List<CartVO> getVOByCids(Integer uid, Integer[] cids);
}