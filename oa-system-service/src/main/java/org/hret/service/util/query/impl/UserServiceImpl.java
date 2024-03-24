package org.hret.service.util.query.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hret.entity.utils.query.User;
import org.hret.mapper.util.query.UserMapper;
import org.hret.service.util.query.UserService;
import org.springframework.stereotype.Service;

/**
 * Author:HRET Milky Way
 * Date:2024/3/24
 * version:1.0
 * @author HRET
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
