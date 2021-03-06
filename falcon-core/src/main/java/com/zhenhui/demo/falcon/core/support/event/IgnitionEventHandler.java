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
package com.zhenhui.demo.falcon.core.support.event;

import java.util.Collection;
import java.util.Collections;

import com.zhenhui.demo.falcon.core.domain.Device;
import com.zhenhui.demo.falcon.core.domain.Event;
import com.zhenhui.demo.falcon.core.domain.EventType;
import com.zhenhui.demo.falcon.core.domain.Position;
import com.zhenhui.demo.falcon.core.server.ServerConnector;
import com.zhenhui.demo.falcon.core.support.handler.AbstractEventHandler;

public class IgnitionEventHandler extends AbstractEventHandler {

    public IgnitionEventHandler(ServerConnector connector) {
        super(connector);
    }

    @Override
    protected Collection<Event> analyzePosition(Position position) {
        Device device = connector.getContext().getDeviceService().getDevice(position.getDeviceId());
        if (device == null || !positionService().isLastPosition(position)) {
            return null;
        }

        Collection<Event> result = null;

        if (position.hasKey(Position.KEY_IGNITION)) {
            boolean ignition = position.getBoolean(Position.KEY_IGNITION);

            Position lastPosition = positionService().getLastPosition(position.getDeviceId());
            if (lastPosition != null && lastPosition.hasKey(Position.KEY_IGNITION)) {
                boolean oldIgnition = lastPosition.getBoolean(Position.KEY_IGNITION);

                if (ignition && !oldIgnition) {
                    result = Collections.singleton(
                            new Event(EventType.TYPE_IGNITION_ON, position.getDeviceId(), position.getId()));
                } else if (!ignition && oldIgnition) {
                    result = Collections.singleton(
                            new Event(EventType.TYPE_IGNITION_OFF, position.getDeviceId(), position.getId()));
                }
            }
        }
        return result;
    }

}
