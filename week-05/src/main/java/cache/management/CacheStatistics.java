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
package cache.management;

import javax.cache.management.CacheStatisticsMXBean;

/**
 * Cache Statistics
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 * Date : 2021-04-13
 */
public interface CacheStatistics extends CacheStatisticsMXBean {

    org.geektimes.cache.management.CacheStatistics reset();

    org.geektimes.cache.management.CacheStatistics cacheHits();

    org.geektimes.cache.management.CacheStatistics cacheGets();

    org.geektimes.cache.management.CacheStatistics cachePuts();

    org.geektimes.cache.management.CacheStatistics cacheRemovals();

    org.geektimes.cache.management.CacheStatistics cacheEvictions();

    org.geektimes.cache.management.CacheStatistics cacheGetsTime(long costTime);

    org.geektimes.cache.management.CacheStatistics cachePutsTime(long costTime);

    org.geektimes.cache.management.CacheStatistics cacheRemovesTime(long costTime);

}
