package io.github.dougcodez.minealert.user;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;

public class MiningUserManager {

    private Map<String, MiningUser> miningUserMap = Maps.newConcurrentMap();

    public void addPlayer(Player player){
        miningUserMap.putIfAbsent(player.getUniqueId().toString(), new MiningUser(player));
    }

    public void removePlayer(Player player){
        miningUserMap.remove(player.getUniqueId().toString());
    }

    public MiningUser getUser(Player player){
        return miningUserMap.get(player.getUniqueId().toString());
    }

    public Collection<MiningUser> getUsers(){
        return miningUserMap.values();
    }

    public Map<String, MiningUser> getMiningUserMap(){
        return miningUserMap;
    }
}
