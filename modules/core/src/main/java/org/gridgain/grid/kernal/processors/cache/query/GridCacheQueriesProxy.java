/* @java.file.header */

/*  _________        _____ __________________        _____
 *  __  ____/___________(_)______  /__  ____/______ ____(_)_______
 *  _  / __  __  ___/__  / _  __  / _  / __  _  __ `/__  / __  __ \
 *  / /_/ /  _  /    _  /  / /_/ /  / /_/ /  / /_/ / _  /  _  / / /
 *  \____/   /_/     /_/   \_,__/   \____/   \__,_/  /_/   /_/ /_/
 */

package org.gridgain.grid.kernal.processors.cache.query;

import org.gridgain.grid.*;
import org.gridgain.grid.cache.*;
import org.gridgain.grid.cache.query.*;
import org.gridgain.grid.kernal.processors.cache.*;
import org.gridgain.grid.lang.*;
import org.gridgain.grid.util.typedef.internal.*;
import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;

/**
 * Per-projection queries object returned to user.
 */
public class GridCacheQueriesProxy<K, V> implements GridCacheQueriesEx<K, V>, Externalizable {
    /** */
    private static final long serialVersionUID = 0L;

    /** */
    private static final ThreadLocal<GridCacheProjectionImpl> stash = new ThreadLocal<>();

    /** */
    private GridCacheGateway<K, V> gate;

    /** */
    private GridCacheProjectionImpl<K, V> prj;

    /** */
    private GridCacheQueriesEx<K, V> delegate;

    /**
     * Required by {@link Externalizable}.
     */
    public GridCacheQueriesProxy() {
        // No-op.
    }

    /**
     * Create cache queries implementation.
     *
     * @param cctx Сontext.
     * @param prj Optional cache projection.
     * @param delegate Delegate object.
     */
    public GridCacheQueriesProxy(GridCacheContext<K, V> cctx, @Nullable GridCacheProjectionImpl<K, V> prj,
        GridCacheQueriesEx<K, V> delegate) {
        assert cctx != null;
        assert delegate != null;

        gate = cctx.gate();

        this.prj = prj;
        this.delegate = delegate;
    }

    /**
     * Gets cache projection.
     *
     * @return Cache projection.
     */
    public GridCacheProjection<K, V> projection() {
        return prj;
    }

    /** {@inheritDoc} */
    @Override public GridCacheQuery<Map.Entry<K, V>> createSqlQuery(Class<?> cls, String clause) {
        GridCacheProjectionImpl<K, V> prev = gate.enter(prj);

        try {
            return delegate.createSqlQuery(cls, clause);
        }
        finally {
            gate.leave(prev);
        }
    }

    /** {@inheritDoc} */
    @Override public GridCacheQuery<Map.Entry<K, V>> createSqlQuery(String clsName, String clause) {
        GridCacheProjectionImpl<K, V> prev = gate.enter(prj);

        try {
            return delegate.createSqlQuery(clsName, clause);
        }
        finally {
            gate.leave(prev);
        }
    }

    /** {@inheritDoc} */
    @Override public GridCacheQuery<List<?>> createSqlFieldsQuery(String qry) {
        GridCacheProjectionImpl<K, V> prev = gate.enter(prj);

        try {
            return delegate.createSqlFieldsQuery(qry);
        }
        finally {
            gate.leave(prev);
        }
    }

    /** {@inheritDoc} */
    @Override public GridCacheQuery<Map.Entry<K, V>> createFullTextQuery(Class<?> cls, String search) {
        GridCacheProjectionImpl<K, V> prev = gate.enter(prj);

        try {
            return delegate.createFullTextQuery(cls, search);
        }
        finally {
            gate.leave(prev);
        }
    }

    /** {@inheritDoc} */
    @Override public GridCacheQuery<Map.Entry<K, V>> createFullTextQuery(String clsName, String search) {
        GridCacheProjectionImpl<K, V> prev = gate.enter(prj);

        try {
            return delegate.createFullTextQuery(clsName, search);
        }
        finally {
            gate.leave(prev);
        }
    }

    /** {@inheritDoc} */
    @Override public GridCacheQuery<Map.Entry<K, V>> createScanQuery(@Nullable GridBiPredicate<K, V> filter) {
        GridCacheProjectionImpl<K, V> prev = gate.enter(prj);

        try {
            return delegate.createScanQuery(filter);
        }
        finally {
            gate.leave(prev);
        }
    }

    /** {@inheritDoc} */
    @Override public GridCacheContinuousQuery<K, V> createContinuousQuery() {
        GridCacheProjectionImpl<K, V> prev = gate.enter(prj);

        try {
            return delegate.createContinuousQuery();
        }
        finally {
            gate.leave(prev);
        }
    }

    /** {@inheritDoc} */
    @Override public GridFuture<?> rebuildIndexes(Class<?> cls) {
        GridCacheProjectionImpl<K, V> prev = gate.enter(prj);

        try {
            return delegate.rebuildIndexes(cls);
        }
        finally {
            gate.leave(prev);
        }
    }

    /** {@inheritDoc} */
    @Override public GridFuture<?> rebuildIndexes(String typeName) {
        GridCacheProjectionImpl<K, V> prev = gate.enter(prj);

        try {
            return delegate.rebuildIndexes(typeName);
        }
        finally {
            gate.leave(prev);
        }
    }

    /** {@inheritDoc} */
    @Override public GridFuture<?> rebuildAllIndexes() {
        GridCacheProjectionImpl<K, V> prev = gate.enter(prj);

        try {
            return delegate.rebuildAllIndexes();
        }
        finally {
            gate.leave(prev);
        }
    }

    /** {@inheritDoc} */
    @Override public GridCacheQueryMetrics metrics() {
        GridCacheProjectionImpl<K, V> prev = gate.enter(prj);

        try {
            return delegate.metrics();
        }
        finally {
            gate.leave(prev);
        }
    }

    /** {@inheritDoc} */
    @Override public void resetMetrics() {
        GridCacheProjectionImpl<K, V> prev = gate.enter(prj);

        try {
            delegate.resetMetrics();
        }
        finally {
            gate.leave(prev);
        }
    }

    /** {@inheritDoc} */
    @Override public Collection<GridCacheSqlMetadata> sqlMetadata() throws GridException {
        GridCacheProjectionImpl<K, V> prev = gate.enter(prj);

        try {
            return delegate.sqlMetadata();
        }
        finally {
            gate.leave(prev);
        }
    }

    /** {@inheritDoc} */
    @Override public GridCacheQuery<List<?>> createSqlFieldsQuery(String qry, boolean incMeta) {
        GridCacheProjectionImpl<K, V> prev = gate.enter(prj);

        try {
            return delegate.createSqlFieldsQuery(qry, incMeta);
        }
        finally {
            gate.leave(prev);
        }
    }

    /** {@inheritDoc} */
    @Override public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(prj);
    }

    /** {@inheritDoc} */
    @Override public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        stash.set((GridCacheProjectionImpl)in.readObject());
    }

    /**
     * Reconstructs object on unmarshalling.
     *
     * @return Reconstructed object.
     * @throws ObjectStreamException Thrown in case of unmarshalling error.
     */
    @SuppressWarnings("unchecked")
    private Object readResolve() throws ObjectStreamException {
        try {
            GridCacheProjectionImpl<K, V> prj = stash.get();

            return new GridCacheProxyImpl<>(prj.context(), prj, prj).queries();
        }
        catch (Exception e) {
            throw U.withCause(new InvalidObjectException(e.getMessage()), e);
        }
        finally {
            stash.remove();
        }
    }
}
