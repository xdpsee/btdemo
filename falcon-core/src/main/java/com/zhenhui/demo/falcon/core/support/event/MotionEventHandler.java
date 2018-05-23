/*
 * Copyright 2016 Anton Tananaev (anton@traccar.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhenhui.demo.falcon.core.support.event;

import java.util.Collection;
import java.util.Collections;

import com.zhenhui.demo.falcon.core.domain.Configs;
import com.zhenhui.demo.falcon.core.domain.Device;
import com.zhenhui.demo.falcon.core.domain.Event;
import com.zhenhui.demo.falcon.core.domain.EventType;
import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.server.ServerConnector;
import com.zhenhui.demo.falcon.core.support.handler.AbstractEventHandler;

public class MotionEventHandler extends AbstractEventHandler {

    private double speedThreshold;

    public MotionEventHandler(ServerConnector connector) {
        super(connector);
        speedThreshold = configs().getDouble(Configs.EVENT_MOTION_SPEED_THRESHOLD, 0.01);
    }

    @Override
    protected Collection<Event> analyzePosition(Position position) {

        Device device = deviceService().getDevice(position.getDeviceId());
        if (null == device) {
            return null;
        }
        if (!positionService().isLastPosition(position) || !position.isLocated()) {
            return null;
        }

        double speed = position.getSpeed(), oldSpeed = 0;
        Position lastPosition = positionService().getLastPosition(position.getDeviceId());
        if (lastPosition != null) {
            oldSpeed = lastPosition.getSpeed();
        }

        if (speed > speedThreshold && oldSpeed <= speedThreshold) {
            return Collections.singleton(
                    new Event(EventType.TYPE_DEVICE_MOVING, position.getDeviceId(), position.getId()));
        } else if (speed <= speedThreshold && oldSpeed > speedThreshold) {
            return Collections.singleton(
                    new Event(EventType.TYPE_DEVICE_STOPPED, position.getDeviceId(), position.getId()));
        }

        return null;
    }

}
