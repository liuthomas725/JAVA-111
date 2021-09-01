/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.event;

import org.EventListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.event.reactive.ListenerSubscriberAdapter;
import org.stream.SimplePublisher;

import java.util.Date;
import java.util.EventObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Distributed {@link EventObject} Publisher
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class DistributedEventPublisher {

    private final SimplePublisher<EventObject> simplePublisher;

    private final ZkClient zkClient;

    private final ExecutorService executorService;

    public DistributedEventPublisher(String uri) {
        simplePublisher = new SimplePublisher();
        this.zkClient = new ZkClient(uri);
        // Build-in listener
        addEventListener(event -> {
            if (event instanceof DistributedEventObject) {
                // Event -> Pub/Sub
                zkClient.createEphemeral("test",event);
            }
        });

        this.executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {

            zkClient.subscribeDataChanges("test", new IZkDataListener() {
                @Override
                public void handleDataChange(String s, Object o) throws Exception {
                    publish(new EventObject(o));
                }

                @Override
                public void handleDataDeleted(String s) throws Exception {

                }
            });

        });
    }

    public void publish(Object event) {
//        simplePublisher.publish(new EventObject(event));
        simplePublisher.publish(new DistributedEventObject(event));
    }

    private void publish(EventObject event) {
//        simplePublisher.publish(new EventObject(event));
        simplePublisher.publish(event);
    }

    public void addEventListener(EventListener eventListener) {
        simplePublisher.subscribe(new ListenerSubscriberAdapter(eventListener));
    }

    public void close() {
        zkClient.close();
        executorService.shutdown();
    }

    public static void main(String[] args) {
        DistributedEventPublisher eventPublisher = new DistributedEventPublisher("redis://127.0.0.1:6379");

        // Publish Event
        eventPublisher.publish(String.valueOf(new Date()));

        eventPublisher.close();
    }
}
