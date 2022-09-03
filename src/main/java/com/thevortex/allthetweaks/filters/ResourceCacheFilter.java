package com.thevortex.allthetweaks.filters;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

import java.util.Objects;

/**
 * Temporary workaround for logspam caused by <a href="https://github.com/MinecraftForge/MinecraftForge/issues/8962">MinecraftForge#8962</a>
 */
@Plugin(name = "ResourceCacheFilter", category = Node.CATEGORY, elementType = Filter.ELEMENT_TYPE)
public class ResourceCacheFilter extends AbstractFilter {
    @Override
    public Result filter(LogEvent event) {
        Message message = event.getMessage();
        if (Objects.equals(message.getFormat(), "Failed to cache resources, the directory does not exist!")) {
            return Result.DENY;
        } else {
            return Result.NEUTRAL;
        }
    }
}
