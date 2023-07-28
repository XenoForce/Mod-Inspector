package mod.blocks.units;

import arc.*;
import arc.graphics.*;
import arc.struct.*;
import arc.util.*;

import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;

import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.entities.units.*;

import mindustry.graphics.*;
import mindustry.type.*;

import mindustry.world.blocks.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

// import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

// import mod.units.*;


public class Unit_Factory_X extends UnitFactory {
	
	//---------------------------------------------------------------//
	//  Constructor                                                  //
	//---------------------------------------------------------------//
	public Unit_Factory_X() {
		
		super("unit-factory-x");
		
		localizedName = "Unit Factory (Inspector)";
		
		var zeroItems = new ItemStack[0];
		
		ItemStack[] zero = {};
		
		requirements( Category.units, zeroItems );
		
		plans = Seq.with(
			// new UnitPlan( X_Unit_Types.collaris_x , 180f, zero),
			// new UnitPlan( X_Unit_Types.conquer_x  , 180f, zero),
			new UnitPlan( UnitTypes.flare, 180f, zero)
		);
		
		size = 3;
		
		// consumePower(1.2f);
		
	} //Constructor
	
} //class
