package com.lulan.shincolle.init;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.lulan.shincolle.ShinColle;
import com.lulan.shincolle.entity.*;
import com.lulan.shincolle.entity.renderentity.*;
import com.lulan.shincolle.item.BasicEntityItem;
import com.lulan.shincolle.reference.Reference;
import com.lulan.shincolle.utility.LogHelper;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
// register natural spawns for entities
// EntityRegistry.addSpawn(MyEntity.class, spawnProbability, minSpawn, maxSpawn, enumCreatureType, [spawnBiome]);
// See the constructor in BiomeGenBase.java to see the rarity of vanilla mobs; Sheep are probability 10 while Endermen are probability 1
// minSpawn and maxSpawn are about how groups of the entity spawn
// enumCreatureType represents the "rules" Minecraft uses to determine spawning, based on creature type. By default, you have three choices:
//    EnumCreatureType.creature uses rules for animals: spawn everywhere it is light out.
//    EnumCreatureType.monster uses rules for monsters: spawn everywhere it is dark out.
//    EnumCreatureType.waterCreature uses rules for water creatures: spawn only in water.
// [spawnBiome] is an optional parameter of type BiomeGenBase that limits the creature spawn to a single biome type. Without this parameter, it will spawn everywhere. 
// For the biome type you can use an list, but unfortunately the built-in biomeList contains
// null entries and will crash, so you need to clean up that list.
// Diesieben07 suggested the following code to remove the nulls and create list of all biomes:
// BiomeGenBase[] allBiomes = Iterators.toArray(Iterators.filter(Iterators.forArray(BiomeGenBase.getBiomeGenArray()), Predicates.notNull()), BiomeGenBase.class);
// example
// EntityRegistry.addSpawn(EntityLion.class, 6, 1, 5, EnumCreatureType.creature, BiomeGenBase.savanna); //change the values to vary the spawn rarity, biome, etc.              
// EntityRegistry.addSpawn(EntityElephant.class, 10, 1, 5, EnumCreatureType.creature, BiomeGenBase.savanna); //change the values to vary the spawn rarity, biome, etc.              
*/
public class ModEntity {
	
	private static int modEntityID = 0;

	public static void init() {
		//register test entity
//		createEntityGlobalID(EntityTest.class, "EntityTest", 0x20FF45, 0x0040FF);
		
		//register ship entity
		createEntity(EntityBattleshipTa.class, "EntityBattleshipTa", modEntityID++);
		createEntity(EntityRensouhouS.class, "EntityRensouhouS", modEntityID++);
		createEntity(EntityBattleshipRe.class, "EntityBattleshipRe", modEntityID++);
		createEntity(EntityCarrierWo.class, "EntityCarrierWo", modEntityID++);
		createEntity(EntityDestroyerI.class, "EntityDestroyerI", modEntityID++);
		createEntity(EntityDestroyerRo.class, "EntityDestroyerRo", modEntityID++);
		createEntity(EntityDestroyerHa.class, "EntityDestroyerHa", modEntityID++);
		createEntity(EntityDestroyerNi.class, "EntityDestroyerNi", modEntityID++);
		createEntity(EntityDestroyerShimakaze.class, "EntityDestroyerShimakaze", modEntityID++);
		createEntity(EntityRensouhou.class, "EntityRensouhou", modEntityID++);
		createEntity(EntityDestroyerShimakazeBoss.class, "EntityDestroyerShimakazeBoss", modEntityID++);
		createEntity(EntityRensouhouBoss.class, "EntityRensouhouBoss", modEntityID++);
		createEntity(EntityHeavyCruiserRi.class, "EntityHeavyCruiserRi", modEntityID++);
				
		//register projectile entity
		createProjectileEntity(EntityAbyssMissile.class, "EntityAbyssMissile", modEntityID++);
		createProjectileEntity(EntityAirplane.class, "EntityAirplane", modEntityID++);
		createProjectileEntity(EntityAirplaneTakoyaki.class, "EntityAirplaneTakoyaki", modEntityID++);
	
		//register render entity
		createProjectileEntity(EntityRenderLargeShipyard.class, "EntityRenderLargeShipyard", modEntityID++);
		createProjectileEntity(EntityRenderVortex.class, "EntityRenderVortex", modEntityID++);

		//register item entity
		createItemEntity(BasicEntityItem.class, "BasicEntityItem", modEntityID++);
		
	}
	
//	//mob自然生成方法, 必須放在postInit才呼叫, 以取得全部mod註冊的全部biome
//	public static void initNaturalSpawn() {
//		//register entity natrual spawn
//		//spawn in ALL ocean biome
//		BiomeGenBase[] allBiomes = Iterators.toArray(Iterators.filter(Iterators.forArray(BiomeGenBase.getBiomeGenArray()), Predicates.notNull()), BiomeGenBase.class);
//		
//		for(int i = 0; i < allBiomes.length; ++i) {
//			if(BiomeDictionary.isBiomeOfType(allBiomes[i], BiomeDictionary.Type.OCEAN)) {
//				EntityRegistry.addSpawn(EntityDestroyerShimakazeBoss.class, 1, 1, 1, EnumCreatureType.creature, BiomeGenBase.savanna);
//			}
//		}
//	}
	
	//登錄生物方法
	//參數: 該生物class, 生物名稱, 怪物蛋背景色, 怪物蛋斑點色
	public static void createEntity(Class entityClass, String entityName, int entityId){
		//登錄參數: 生物class, 生物名稱, 生物id, mod副本, 追蹤更新距離, 更新時間間隔, 是否發送同步封包(高速entity必須true才會顯示平順)
		EntityRegistry.registerModEntity(entityClass, entityName, entityId, ShinColle.instance, 48, 1, true);
	}
	
	//登錄非生物方法 (無生怪蛋)
	//參數: 該生物class, 生物名稱
	public static void createProjectileEntity(Class entityClass, String entityName, int entityId){
		//登錄參數: 生物class, 生物名稱, 生物id, mod副本, 追蹤更新距離, 更新時間間隔, 是否發送速度封包
		EntityRegistry.registerModEntity(entityClass, entityName, entityId, ShinColle.instance, 48, 1, true);
	}
	
	//登錄item entity方法 (無生怪蛋)
	//參數: 該生物class, 生物名稱
	public static void createItemEntity(Class entityClass, String entityName, int entityId){
		//登錄參數: 生物class, 生物名稱, 生物id, mod副本, 追蹤更新距離, 更新時間間隔, 是否發送速度封包
		EntityRegistry.registerModEntity(entityClass, entityName, entityId, ShinColle.instance, 48, 1, false);
	}
	
	//使用官方共通id登錄生物
	//參數: 該生物class, 生物名稱
	public static void createEntityGlobalID(Class entityClass, String entityName, int backColor, int spotColor){
		int entityId = modEntityID++;
		
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, entityId);
		//登錄參數: 生物class, 生物名稱, 生物id, mod副本, 追蹤更新距離, 更新時間間隔, 是否發送速度封包
		EntityRegistry.registerModEntity(entityClass, entityName, entityId, ShinColle.instance, 64, 1, false);
		//登錄怪物生物蛋: 生物id, 生成蛋資訊(生物id,背景色,斑點色)
		EntityList.entityEggs.put(Integer.valueOf(entityId), new EntityList.EntityEggInfo(entityId, backColor, spotColor));
	}
	

}
