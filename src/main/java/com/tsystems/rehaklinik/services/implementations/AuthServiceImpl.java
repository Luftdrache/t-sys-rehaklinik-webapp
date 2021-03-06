package com.tsystems.rehaklinik.services.implementations;

import com.tsystems.rehaklinik.dao.interfaces.AuthDAO;
import com.tsystems.rehaklinik.entities.AuthenticationData;
import com.tsystems.rehaklinik.services.interfaces.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("AuthService")
@Transactional
public class AuthServiceImpl implements AuthService {

    private static Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final AuthDAO authDAO;


    @Override
    public AuthenticationData findUserByUsername(String username) {
        logger.info("MedHelper_LOGS: AuthServiceImpl: findUserByUsername() --> finding user authentication data");
        return authDAO.findByUsername(username);
    }


    @Autowired
    public AuthServiceImpl(AuthDAO authDAO) {
        this.authDAO = authDAO;
    }
}
