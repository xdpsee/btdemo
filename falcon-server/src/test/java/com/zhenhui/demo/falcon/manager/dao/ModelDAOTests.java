package com.zhenhui.demo.falcon.manager.dao;

import java.util.Arrays;

import com.zhenhui.demo.falcon.core.domain.CommandType;
import com.zhenhui.demo.falcon.core.domain.Model;
import com.zhenhui.demo.falcon.server.Application;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@SuppressWarnings("SpringJavaAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@FixMethodOrder
@Transactional
public class ModelDAOTests {

    @Autowired
    private ModelDAO modelDAO;

    @Test
    public void testCreate() {

        Model model = new Model();
        model.setName("TEST");
        model.setProtocol("mobile");
        model.setSupportedCommands(Arrays.asList(CommandType.TYPE_CUSTOM));

        modelDAO.createModel(model);

        Model m = modelDAO.queryModel("TEST");
        assertNotNull(m);
    }

    @Test
    public void testQuery() {
        Model model = modelDAO.queryModel("NONE");
        assertNull(model);

        model = new Model();
        model.setName("NONE");
        model.setProtocol("mobile");
        model.setSupportedCommands(Arrays.asList(CommandType.TYPE_CUSTOM));

        modelDAO.createModel(model);

        Model m = modelDAO.queryModel("NONE");
        assertNotNull(m);

    }
}
