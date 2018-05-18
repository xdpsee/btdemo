/*
 * Copyright 2016 Anton Tananaev (anton@traccar.org)
 * Copyright 2016 Andrey Kunitsyn (andrey@traccar.org)
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
package com.zhenhui.demo.falcon.core.surport.event;

import java.util.Collection;
import java.util.Collections;

import com.zhenhui.demo.falcon.core.domain.Device;
import com.zhenhui.demo.falcon.core.domain.Event;
import com.zhenhui.demo.falcon.core.domain.EventType;
import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.surport.Context;
import com.zhenhui.demo.falcon.core.surport.handler.AbstractEventHandler;

public class MaintenanceEventHandler extends AbstractEventHandler {

    public static final String ATTRIBUTE_MAINTENANCE_START = "maintenance.start";
    public static final String ATTRIBUTE_MAINTENANCE_INTERVAL = "maintenance.interval";


    @Override
    protected Collection<Event> analyzePosition(Position position) {
        Device device = Context.deviceService().queryDevice(position.getDeviceId());
        if (device == null || !Context.positionService().isLastPosition(position)) {
            return null;
        }

        double maintenanceInterval = device.getDouble(ATTRIBUTE_MAINTENANCE_INTERVAL);
        if (maintenanceInterval <= 0) {
            return null;
        }

        double maintenanceStart = device.getDouble(ATTRIBUTE_MAINTENANCE_START);

        double oldTotalDistance = 0.0;
        Position lastPosition = Context.positionService().getLastPosition(position.getDeviceId());
        if (lastPosition != null) {
            oldTotalDistance = lastPosition.getDouble(Position.KEY_TOTAL_DISTANCE);
        }

        double newTotalDistance = position.getDouble(Position.KEY_TOTAL_DISTANCE);

        oldTotalDistance -= maintenanceStart;
        newTotalDistance -= maintenanceStart;

        if ((long) (oldTotalDistance / maintenanceInterval) < (long) (newTotalDistance / maintenanceInterval)) {
            Event event = new Event(EventType.TYPE_MAINTENANCE, position.getDeviceId(), position.getId());
            event.set(Position.KEY_TOTAL_DISTANCE, newTotalDistance);
            return Collections.singleton(event);
        }

        return null;
    }

}
