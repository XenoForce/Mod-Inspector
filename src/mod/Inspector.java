package mod;

import java.util.*;
import java.util.function.*;

import arc.*;
import arc.func.*;
import arc.struct.*;
import arc.util.*;

import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;
import mindustry.ctype.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.unit.*;
import mindustry.world.*;

// import mod.blocks.turrets.*;
import mod.blocks.units.*;
// import mod.units.*;


public class Inspector extends Mod {
	
	//---------------------------------------------------------------//
	//  Constructor                                                  //
	//---------------------------------------------------------------//
	public Inspector() {
		Log.info("Loaded Inspector constructor.");
	}
	
	
	//---------------------------------------------------------------//
	//  NOTES:                                                       //
	//                                                               //
	//  Item          extends  UnlockableContent                     //
	//  Liquid        extends  UnlockableContent                     //
	//                                                               //
	//  UnitType      extends  UnlockableContent                     //
	//                                                               //
	//  Planet        extends  UnlockableContent                     //
	//  SectorPreset  extends  UnlockableContent                     //
	//                                                               //
	//  BaseTurret    extends  Block  extends  UnlockableContent     //
	//  Conveyor      extends  Block  extends  UnlockableContent     //
	//                                                               //
	//  UnlockableContent  extends MappableContent  extends Content  //
	//---------------------------------------------------------------//
	
	
	//---------------------------------------------------------------//
	//  loadContent()                                                //
	//---------------------------------------------------------------//
	@Override
	public void loadContent() {
		
		Log.info("Begin: Inspector.loadContent().");
		
		// --- Grab the Content: ------------------------
		
		List<UnlockableContent> arrContent = new ArrayList<>();
		
		Cons<Content> grabAction = x -> {
			if (x instanceof UnlockableContent) {
				arrContent.add( (UnlockableContent) x );
			}
		};
		
		Vars.content.each( grabAction );
		
		// --- Unlock the Content: ----------------------
		
		ItemStack[] zero = {};
		
		Consumer<UnlockableContent> unlockAction = x -> {
			x.unlock();
			
			if (x instanceof Block) {
				Block blk = (Block) x;
				blk.requirements = zero;
			}
		};
		
		arrContent.forEach( unlockAction );
		
		// - - - - - - - - - - - - - - - - - - - - - -
		
		modifySectors();
		
		modifyAllSectors();
		
		Log.info("End of: Inspector.loadContent().");
		
	} //loadContent()
	
	
	//---------------------------------------------------------------//
	//  modifyAllSectors()                                           //
	//---------------------------------------------------------------//
	public void modifyAllSectors() {
		
		// List<SectorPreset> arr = new ArrayList<>();
		
		Seq<Sector> arr = Planets.serpulo.sectors;
		
		Cons<Sector> action = x -> {
			x.info.winWave = 3;
			// x.difficulty = 5;
			// x.startWaveTimeMultiplier = 4f;
		};
		
		arr.each( action );
		
	} //modifyAllSectors()
	
	
	//---------------------------------------------------------------//
	//  modifySectors()                                              //
	//---------------------------------------------------------------//
	public void modifySectors() {
		
		List<SectorPreset> arr = new ArrayList<>();
		
		arr.add( SectorPresets.groundZero );
		arr.add( SectorPresets.frozenForest );
		arr.add( SectorPresets.craters );
		arr.add( SectorPresets.biomassFacility );
		arr.add( SectorPresets.stainedMountains );
		arr.add( SectorPresets.ruinousShores );
		arr.add( SectorPresets.windsweptIslands );
		arr.add( SectorPresets.tarFields );
		arr.add( SectorPresets.impact0078 );
		arr.add( SectorPresets.nuclearComplex );
		arr.add( SectorPresets.desolateRift );
		arr.add( SectorPresets.coastline );
		
		Consumer<SectorPreset> action = x -> {
			x.captureWave = 3;
			x.difficulty = 5;
			x.startWaveTimeMultiplier = 4f;
		};
		
		arr.forEach( action );
		
	} //modifySectors()
	
	
	//---------------------------------------------------------------//
	//  other_Interesting_Ideas()                                    //
	//---------------------------------------------------------------//
	private void other_Interesting_Ideas() {
		
		Seq<Block>        blocks  = Vars.content.blocks();
		Seq<Item>         items   = Vars.content.items();
		Seq<Liquid>       liquids = Vars.content.liquids();
		Seq<SectorPreset> sectors = Vars.content.sectors();
		Seq<UnitType>     units   = Vars.content.units();
		Seq<Planet>       planets = Vars.content.planets();
		
		// - - - - - - - - - - - - - - - - - - - - - -
		
		Cons<Block> blockAction = x -> {
			Log.info( x.name );
		};
		
		blocks.each( blockAction );
		
	} //other_Interesting_Ideas()
	
	
} //class
