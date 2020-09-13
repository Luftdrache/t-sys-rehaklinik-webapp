package com.tsystems.rehaklinik.dao.implementations;


import com.tsystems.rehaklinik.dao.interfaces.AuthDAO;
import com.tsystems.rehaklinik.entities.AuthenticationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class AuthDAOImpl implements AuthDAO {

    private static Logger logger = LoggerFactory.getLogger(AuthDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AuthenticationData findByUsername(String username) {
        logger.info("MedHelper_LOGS: AuthDAOImpl: Finding user data by username");
        TypedQuery<AuthenticationData> query = entityManager.createQuery(
                "SELECT u FROM AuthenticationData u WHERE u.username LIKE :username", AuthenticationData.class);
        query.setParameter("username", username);
        try {
            return query.getSingleResult();
        } catch (NoResultException exception) {
            logger.error("MedHelper_LOGS: AuthDAOImpl: NoResultException exception thrown");
            return null;
        }
    }
}
