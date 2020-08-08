package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dao.AuthDAO;
import com.tsystems.rehaklinik.entities.AuthenticationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("AuthService")
@Transactional
public class AuthServiceImpl implements AuthService{

    private static Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final AuthDAO authDAO;


    @Override
    public AuthenticationData findUserByUsername(String username) {
        logger.info("MedHelper_LOGS: AuthServiceImpl: Finding user authentication data");
        AuthenticationData authenticationData =  authDAO.findByUsername(username);
        return authenticationData;
    }


    @Autowired
    public AuthServiceImpl(AuthDAO authDAO) {
        this.authDAO = authDAO;
    }
}
