package com.mongo.utils;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmbeddedMongoDBUtils {

    private static MongodExecutable mongodExecutable;

    @SneakyThrows
    public static void startMongoDb(String host, int port) {
        log.info(">>>>>>>>>> Starting Embedded MongoDB <<<<<<<<<<<<");
        ImmutableMongodConfig mongodConfig = ImmutableMongodConfig.builder().version(Version.Main.PRODUCTION)
                .net(new Net(host, port, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
    }

    public static void stopMongoDb() {
        log.info(">>>>>>>>>> Stopping Embedded MongoDB <<<<<<<<<<<<");
        mongodExecutable.stop();
    }

}
