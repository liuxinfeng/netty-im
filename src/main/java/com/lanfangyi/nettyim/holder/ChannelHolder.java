package com.lanfangyi.nettyim.holder;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 此类存储channel和用户的信息
 *
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/21 8:36 PM
 */
public class ChannelHolder {

    /**
     * key是userId。
     */
    private static ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<>(100);

    private static ConcurrentHashMap<String, Channel> channelNoUserId = new ConcurrentHashMap<>(100);

    public static boolean putAndOverWrite(String userKey, Channel channel) {
        return channelMap.put(userKey, channel) != null;
    }

    /**
     * 根据key拿到对应的channel
     *
     * @param userKey 用户的ID
     */
    public static Channel getChannel(String userKey) {
        return channelMap.get(userKey);
    }

    public static List<Channel> allChannel() {
        return (List<Channel>) channelMap.values();
    }

    public static ConcurrentHashMap<String, Channel> all() {
        return channelMap;
    }

    public static boolean putChannelNoUserId(String channelId, Channel channel) {
        return channelNoUserId.put(channelId, channel) != null;
    }

    public static boolean removeChannelNoUserId(String channelId) {
        channelNoUserId.remove(channelId);
        return true;
    }
}
