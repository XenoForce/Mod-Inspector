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

import mindustry.world.blocks.units.UnitFactory.UnitPlan;


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
		
		processUnits( arrContent );
		
		remove_Erekir_Red_Filter();
		
		modifySectors();
		
		modifyAllSectors();
		
		Log.info("End of: Inspector.loadContent().");
		
	} //loadContent()
	
	
	//---------------------------------------------------------------//
	//  processUnits()                                               //
	//---------------------------------------------------------------//
	private void processUnits( List<UnlockableContent>  arrContent ) {
		
		ItemStack[]     zero      = {};
		List<UnitPlan>  arrPlan   = new ArrayList<>();
		List<UnitType>  gameUnits = prepare_BuiltIn_GameUnits();
		
		Consumer<UnlockableContent> action = x -> {
			if (x instanceof UnitType) {
				UnitType ut = (UnitType) x;
				
				if (!gameUnits.contains( ut )) {
					arrPlan.add( new UnitPlan( ut, 180f, zero ));
				} //if
			} //if
		};
		
		arrContent.forEach( action );
		
		Seq<UnitPlan> unitPlans = new Seq<>( arrPlan.size() );
		
		arrPlan.forEach( x -> unitPlans.add(x) );
		
		Unit_Factory_X fact = new Unit_Factory_X();
		fact.plans = unitPlans;
		fact.load();
		fact.unlock();
		
	} //processUnits()
	
	
	//---------------------------------------------------------------//
	//  prepare_BuiltIn_GameUnits()                                  //
	//---------------------------------------------------------------//
	private List<UnitType> prepare_BuiltIn_GameUnits() {
		
		List<UnitType> list = new ArrayList<>();
		
		list.add( UnitTypes.alpha );
		list.add( UnitTypes.beta );
		list.add( UnitTypes.gamma );
		
		list.add( UnitTypes.evoke );
		list.add( UnitTypes.incite );
		list.add( UnitTypes.emanate );
		
		list.add( UnitTypes.missile );
		
		list.add( UnitTypes.flare );
		list.add( UnitTypes.horizon );
		list.add( UnitTypes.zenith );
		list.add( UnitTypes.antumbra );
		list.add( UnitTypes.eclipse );
		
		list.add( UnitTypes.mono );
		list.add( UnitTypes.poly );
		list.add( UnitTypes.mega );
		list.add( UnitTypes.quad );
		list.add( UnitTypes.oct );
		
		list.add( UnitTypes.dagger );
		list.add( UnitTypes.mace );
		list.add( UnitTypes.fortress );
		list.add( UnitTypes.scepter );
		list.add( UnitTypes.reign );
		
		list.add( UnitTypes.nova );
		list.add( UnitTypes.pulsar );
		list.add( UnitTypes.quasar );
		list.add( UnitTypes.vela );
		list.add( UnitTypes.corvus );
		
		list.add( UnitTypes.crawler );
		list.add( UnitTypes.atrax );
		list.add( UnitTypes.spiroct );
		list.add( UnitTypes.arkyid );
		list.add( UnitTypes.toxopid );
		
		list.add( UnitTypes.risso );
		list.add( UnitTypes.minke );
		list.add( UnitTypes.bryde );
		list.add( UnitTypes.sei );
		list.add( UnitTypes.omura );
		
		list.add( UnitTypes.retusa );
		list.add( UnitTypes.oxynoe );
		list.add( UnitTypes.cyerce );
		list.add( UnitTypes.aegires );
		list.add( UnitTypes.navanax );
		
		list.add( UnitTypes.elude );
		list.add( UnitTypes.avert );
		list.add( UnitTypes.obviate );
		list.add( UnitTypes.quell );
		list.add( UnitTypes.disrupt );
		
		list.add( UnitTypes.merui );
		list.add( UnitTypes.cleroi );
		list.add( UnitTypes.anthicus );
		list.add( UnitTypes.tecta );
		list.add( UnitTypes.collaris );
		
		list.add( UnitTypes.stell );
		list.add( UnitTypes.locus );
		list.add( UnitTypes.precept );
		list.add( UnitTypes.vanquish );
		list.add( UnitTypes.conquer );
		
		return list;
	} //prepare_BuiltIn_GameUnits()
	
	
	//---------------------------------------------------------------//
	//  remove_Erekir_Red_Filter()                                   //
	//---------------------------------------------------------------//
	private void remove_Erekir_Red_Filter() {
		
		Vars.renderer.envRenderers.clear();
		
	} //remove_Erekir_Red_Filter()
	
	
	//---------------------------------------------------------------//
	//  modifyAllSectors()                                           //
	//---------------------------------------------------------------//
	private void modifyAllSectors() {
		
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
	private void modifySectors() {
		
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
