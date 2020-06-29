package kc.mocks;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.node.InternalSettingsPreparer;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeValidationException;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.transport.Netty4Plugin;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;


public class LocalElasticSearch {


    public static void start() {
        Settings settings = Settings.builder()
                .put("http.host", "localhost")
                .put("discovery.type", "single-node")
                .put("cluster.name", "test-cluster")
                .put("node.name", "test-node")
                .put(Environment.PATH_HOME_SETTING.getKey(), "/tmp").build();


        Node node = new PluginConfigurableNode(settings, Collections.singletonList(Netty4Plugin.class));

        try {
            node.start();
        } catch (NodeValidationException e) {
            e.printStackTrace();
        }

        //SpringApplication.run(DemoApplication.class, args);
    }

    private static class PluginConfigurableNode extends Node {

        public PluginConfigurableNode(Settings settings, Collection<Class<? extends Plugin>> plugins) {
            super(InternalSettingsPreparer.prepareEnvironment(settings, new HashMap<>(), null, null), plugins, false);
        }
    }




}
