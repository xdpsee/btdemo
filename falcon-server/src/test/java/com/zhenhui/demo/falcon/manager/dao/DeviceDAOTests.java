package com.zhenhui.demo.falcon.manager.dao;

import java.util.Arrays;
import java.util.Date;

import com.zhenhui.demo.falcon.core.domain.CommandType;
import com.zhenhui.demo.falcon.core.domain.Device;
import com.zhenhui.demo.falcon.core.domain.Model;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import com.zhenhui.demo.falcon.core.support.exception.DuplicateDeviceException;
import com.zhenhui.demo.falcon.core.support.exception.ModelNotFoundException;
import com.zhenhui.demo.falcon.core.support.exception.ProtocolException;
import com.zhenhui.demo.falcon.server.Application;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

@SuppressWarnings("SpringJavaAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@FixMethodOrder
@Transactional
public class DeviceDAOTests {

    @Autowired
    protected ModelDAO modelDAO;
    @Autowired
    private DeviceDAO deviceDAO;

    @Test
    public void testQuery() throws Exception {

        UniqueID deviceId = UniqueID.fromString("IMEI-880118621816233");
        Device device = deviceDAO.queryDevice(deviceId);
        assertNull(device);

        Model model = new Model();
        model.setName("TEST");
        model.setProtocol("mobile");
        model.setSupportedCommands(Arrays.asList(CommandType.TYPE_CUSTOM));

        modelDAO.createModel(model);
        assertNotNull(model);

        device = new Device();
        device.setId(deviceId);
        device.setModel("TEST");
        device.setProtocol("mobile");
        device.setGmtCreate(new Date());
        device.setGmtUpdate(new Date());

        deviceDAO.createDevice(device);

        Device d = deviceDAO.queryDevice(deviceId);
        assertNotNull(d);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testModelNotFound() throws Exception {

        Device device = new Device();
        device.setId(UniqueID.fromString("IMEI-880118621816233"));
        device.setModel("TEST-R");
        device.setProtocol("mobile");
        device.setGmtCreate(new Date());
        device.setGmtUpdate(new Date());
        deviceDAO.createDevice(device);
    }

    @Test(expected = ProtocolException.class)
    public void testProtocolException() throws Exception {
        Model model = new Model();
        model.setName("TEST");
        model.setProtocol("mobile");
        model.setSupportedCommands(Arrays.asList(CommandType.TYPE_CUSTOM));

        modelDAO.createModel(model);
        assertNotNull(model);

        UniqueID deviceId = UniqueID.fromString("IMEI-880118621816233");
        Device device = new Device();
        device.setId(deviceId);
        device.setModel("TEST");
        device.setProtocol("mobile-r");
        device.setGmtCreate(new Date());
        device.setGmtUpdate(new Date());

        deviceDAO.createDevice(device);

    }

    @Test(expected = DuplicateDeviceException.class)
    public void testDuplicateDeviceException() throws Exception {
        Model model = new Model();
        model.setName("TEST");
        model.setProtocol("mobile");
        model.setSupportedCommands(Arrays.asList(CommandType.TYPE_CUSTOM));

        modelDAO.createModel(model);
        assertNotNull(model);

        UniqueID deviceId = UniqueID.fromString("IMEI-880118621816233");
        Device device = new Device();
        device.setId(deviceId);
        device.setModel("TEST");
        device.setProtocol("mobile");
        device.setGmtCreate(new Date());
        device.setGmtUpdate(new Date());

        deviceDAO.createDevice(device);

        Device d = new Device();
        d.setId(deviceId);
        d.setModel("TEST");
        d.setProtocol("mobile");
        d.setGmtCreate(new Date());
        d.setGmtUpdate(new Date());

        deviceDAO.createDevice(d);

    }
}
