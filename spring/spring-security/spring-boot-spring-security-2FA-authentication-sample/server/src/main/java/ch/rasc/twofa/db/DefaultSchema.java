/*
 * This file is generated by jOOQ.
 */
package ch.rasc.twofa.db;


import javax.annotation.processing.Generated;

import org.jooq.Catalog;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = -244406517;

    /**
     * The reference instance of <code></code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }
}
