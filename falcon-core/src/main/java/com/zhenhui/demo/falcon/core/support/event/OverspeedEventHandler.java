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

public class OverspeedEventHandler extends AbstractEventHandler {

    private final boolean notRepeat;

    public OverspeedEventHandler(ServerConnector connector) {
        super(connector);
        notRepeat = configs().getBoolean(Configs.EVENT_OVERSPEED_NOT_REPEAT);
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

        double speed = position.getSpeed();
        double speedLimit = device.getDouble(Device.KEY_SPEED_LIMIT);
        if (speedLimit <= 0.0) {
            return null;
        }

        double oldSpeed = 0;
        if (notRepeat) {
            Position lastPosition = positionService().getLastPosition(position.getDeviceId());
            if (lastPosition != null) {
                oldSpeed = lastPosition.getSpeed();
            }
        }

        if (speed > speedLimit && oldSpeed <= speedLimit) {
            Event event = new Event(EventType.TYPE_DEVICE_OVERSPEED, position.getDeviceId(), position.getId());
            event.set(Event.SPEED, speed);
            event.set(Device.KEY_SPEED_LIMIT, speedLimit);
            return Collections.singleton(event);
        }

        return null;
    }

}


