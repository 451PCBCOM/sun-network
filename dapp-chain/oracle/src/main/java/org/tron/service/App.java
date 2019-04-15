package org.tron.service;

import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;
import org.tron.common.config.Args;
import org.tron.common.utils.WalletUtil;
import org.tron.service.task.ChainTask;
import org.tron.service.task.TaskEnum;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
@Slf4j(topic = "app")
public class App {

  private static String mainGatewayAddress = "172.16.20.52:9092";

  private static String sideChainGatewayAddress = "172.16.22.252:9092";

  private static int fixedThreads = 5;

  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    logger.info("start...");
    Args arg = Args.getInstance();
    arg.setParam(args);

    ChainTask mainChainTask = new ChainTask(TaskEnum.MAIN_CHAIN,
        WalletUtil.encode58Check(arg.getMainchainGateway()),
        mainGatewayAddress, fixedThreads);
    ChainTask sideChainTask = new ChainTask(TaskEnum.SIDE_CHAIN,
        WalletUtil.encode58Check(arg.getSidechainGateway()),
        sideChainGatewayAddress,
        fixedThreads);
    mainChainTask.start();
    sideChainTask.start();
    logger.info("end...");
  }
}
