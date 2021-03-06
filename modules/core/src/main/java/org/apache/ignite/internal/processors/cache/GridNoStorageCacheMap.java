/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.ignite.internal.processors.cache;

import java.util.Collections;
import java.util.Set;
import org.apache.ignite.internal.processors.affinity.AffinityTopologyVersion;
import org.apache.ignite.internal.processors.cache.distributed.dht.GridDhtCacheEntry;
import org.apache.ignite.internal.processors.cache.distributed.dht.GridDhtOffHeapCacheEntry;
import org.jetbrains.annotations.Nullable;

/**
 * Empty cache map that will never store any entries.
 */
public class GridNoStorageCacheMap implements GridCacheConcurrentMap {
    /** Context. */
    private final GridCacheContext ctx;

    /**
     * @param ctx Cache context.
     */
    public GridNoStorageCacheMap(GridCacheContext ctx) {
        this.ctx = ctx;
    }

    /** {@inheritDoc} */
    @Nullable @Override public GridCacheMapEntry getEntry(KeyCacheObject key) {
        return null;
    }

    /** {@inheritDoc} */
    @Override public GridCacheMapEntry putEntryIfObsoleteOrAbsent(AffinityTopologyVersion topVer, KeyCacheObject key,
        @Nullable CacheObject val, boolean create, boolean touch) {
        if (create)
            return ctx.useOffheapEntry() ?
                new GridDhtOffHeapCacheEntry(ctx, topVer, key, key.hashCode(), val) :
                new GridDhtCacheEntry(ctx, topVer, key, key.hashCode(), val);
        else
            return null;
    }

    /** {@inheritDoc} */
    @Override public boolean removeEntry(GridCacheEntryEx entry) {
        throw new AssertionError();
    }

    /** {@inheritDoc} */
    @Override public int size() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override public int publicSize() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override public void incrementPublicSize(GridCacheEntryEx e) {
        // No-op.
    }

    /** {@inheritDoc} */
    @Override public void decrementPublicSize(GridCacheEntryEx e) {
        // No-op.
    }

    /** {@inheritDoc} */
    @Nullable @Override public GridCacheMapEntry randomEntry() {
        return null;
    }

    /** {@inheritDoc} */
    @Override public Set<KeyCacheObject> keySet(CacheEntryPredicate... filter) {
        return Collections.emptySet();
    }

    /** {@inheritDoc} */
    @Override public Iterable<GridCacheMapEntry> entries(CacheEntryPredicate... filter) {
        return Collections.emptySet();
    }

    /** {@inheritDoc} */
    @Override public Iterable<GridCacheMapEntry> allEntries(CacheEntryPredicate... filter) {
        return Collections.emptySet();
    }

    /** {@inheritDoc} */
    @Override public Set<GridCacheMapEntry> entrySet(CacheEntryPredicate... filter) {
        return Collections.emptySet();
    }
}
