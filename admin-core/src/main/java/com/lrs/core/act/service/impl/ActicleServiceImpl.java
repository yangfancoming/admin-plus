package com.lrs.core.act.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lrs.common.constant.ApiResultEnum;
import com.lrs.common.constant.ResponseModel;
import com.lrs.common.dto.PageDTO;
import com.lrs.core.act.entity.Acticle;
import com.lrs.core.act.mapper.ActicleMapper;
import com.lrs.core.act.service.IActicleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rstyro
 * @since 2019-01-16
 */
@Service
public class ActicleServiceImpl extends ServiceImpl<ActicleMapper, Acticle> implements IActicleService {

    @Override
    public ResponseModel getList(PageDTO dto) throws Exception {
        IPage<Acticle> page = new Page<>();
        if(dto.getPageNo() != null){
            page.setCurrent(dto.getPageNo());
        }
        if(dto.getPageSize() != null){
            page.setSize(dto.getPageSize());
        }
        QueryWrapper<Acticle> queryWrapper = new QueryWrapper();
//        if(!StringUtils.isEmpty(dto.getKeyword())){
//            queryWrapper.lambda()
//                    .like(Acticle::getAuther,dto.getKeyword())
//                    .like(Acticle::getContent,dto.getKeyword())
//                    .like(Acticle::getTitle,dto.getKeyword());
//        }
        IPage<Acticle> iPage = this.page(page, queryWrapper);
        return new ResponseModel(ApiResultEnum.SUCCESS,iPage);
    }

    @Override
    public ResponseModel add(Acticle item, HttpSession session) throws Exception {
        this.save(item);
        return new ResponseModel(ApiResultEnum.SUCCESS,null);
    }

    @Override
    public ResponseModel edit(Acticle item, HttpSession session) throws Exception {
        this.updateById(item);
        return new ResponseModel(ApiResultEnum.SUCCESS,null);
    }

    @Override
    public ResponseModel del(Long id, HttpSession session) throws Exception {
        this.removeById(id);
        return new ResponseModel(ApiResultEnum.SUCCESS,null);
    }

    @Override
    public ResponseModel getDetail(Long id) throws Exception {
        Acticle acticle = this.getOne(new QueryWrapper<Acticle>().lambda().eq(Acticle::getId,id));
        return new ResponseModel(ApiResultEnum.SUCCESS,acticle);
    }
}
