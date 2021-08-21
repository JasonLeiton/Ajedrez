// Generated from Scratch2.g4 by ANTLR 4.9.2
package gastmappers.sb2mapper.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Scratch2Parser}.
 */
public interface Scratch2Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(Scratch2Parser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(Scratch2Parser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#spriteObject}.
	 * @param ctx the parse tree
	 */
	void enterSpriteObject(Scratch2Parser.SpriteObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#spriteObject}.
	 * @param ctx the parse tree
	 */
	void exitSpriteObject(Scratch2Parser.SpriteObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#objName}.
	 * @param ctx the parse tree
	 */
	void enterObjName(Scratch2Parser.ObjNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#objName}.
	 * @param ctx the parse tree
	 */
	void exitObjName(Scratch2Parser.ObjNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#scripts}.
	 * @param ctx the parse tree
	 */
	void enterScripts(Scratch2Parser.ScriptsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#scripts}.
	 * @param ctx the parse tree
	 */
	void exitScripts(Scratch2Parser.ScriptsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#script_blocks}.
	 * @param ctx the parse tree
	 */
	void enterScript_blocks(Scratch2Parser.Script_blocksContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#script_blocks}.
	 * @param ctx the parse tree
	 */
	void exitScript_blocks(Scratch2Parser.Script_blocksContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#script_block}.
	 * @param ctx the parse tree
	 */
	void enterScript_block(Scratch2Parser.Script_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#script_block}.
	 * @param ctx the parse tree
	 */
	void exitScript_block(Scratch2Parser.Script_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#position}.
	 * @param ctx the parse tree
	 */
	void enterPosition(Scratch2Parser.PositionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#position}.
	 * @param ctx the parse tree
	 */
	void exitPosition(Scratch2Parser.PositionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(Scratch2Parser.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(Scratch2Parser.CodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#statementArray}.
	 * @param ctx the parse tree
	 */
	void enterStatementArray(Scratch2Parser.StatementArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#statementArray}.
	 * @param ctx the parse tree
	 */
	void exitStatementArray(Scratch2Parser.StatementArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(Scratch2Parser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(Scratch2Parser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#functionBlock}.
	 * @param ctx the parse tree
	 */
	void enterFunctionBlock(Scratch2Parser.FunctionBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#functionBlock}.
	 * @param ctx the parse tree
	 */
	void exitFunctionBlock(Scratch2Parser.FunctionBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#procDef}.
	 * @param ctx the parse tree
	 */
	void enterProcDef(Scratch2Parser.ProcDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#procDef}.
	 * @param ctx the parse tree
	 */
	void exitProcDef(Scratch2Parser.ProcDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#name_function}.
	 * @param ctx the parse tree
	 */
	void enterName_function(Scratch2Parser.Name_functionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#name_function}.
	 * @param ctx the parse tree
	 */
	void exitName_function(Scratch2Parser.Name_functionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#param_type_definition}.
	 * @param ctx the parse tree
	 */
	void enterParam_type_definition(Scratch2Parser.Param_type_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#param_type_definition}.
	 * @param ctx the parse tree
	 */
	void exitParam_type_definition(Scratch2Parser.Param_type_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#param_init_values}.
	 * @param ctx the parse tree
	 */
	void enterParam_init_values(Scratch2Parser.Param_init_valuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#param_init_values}.
	 * @param ctx the parse tree
	 */
	void exitParam_init_values(Scratch2Parser.Param_init_valuesContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(Scratch2Parser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(Scratch2Parser.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#params_value}.
	 * @param ctx the parse tree
	 */
	void enterParams_value(Scratch2Parser.Params_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#params_value}.
	 * @param ctx the parse tree
	 */
	void exitParams_value(Scratch2Parser.Params_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#hatBlock}.
	 * @param ctx the parse tree
	 */
	void enterHatBlock(Scratch2Parser.HatBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#hatBlock}.
	 * @param ctx the parse tree
	 */
	void exitHatBlock(Scratch2Parser.HatBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#whenGreenFlag}.
	 * @param ctx the parse tree
	 */
	void enterWhenGreenFlag(Scratch2Parser.WhenGreenFlagContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#whenGreenFlag}.
	 * @param ctx the parse tree
	 */
	void exitWhenGreenFlag(Scratch2Parser.WhenGreenFlagContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#whenKeyPressed}.
	 * @param ctx the parse tree
	 */
	void enterWhenKeyPressed(Scratch2Parser.WhenKeyPressedContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#whenKeyPressed}.
	 * @param ctx the parse tree
	 */
	void exitWhenKeyPressed(Scratch2Parser.WhenKeyPressedContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#whenClicked}.
	 * @param ctx the parse tree
	 */
	void enterWhenClicked(Scratch2Parser.WhenClickedContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#whenClicked}.
	 * @param ctx the parse tree
	 */
	void exitWhenClicked(Scratch2Parser.WhenClickedContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#whenSceneStarts}.
	 * @param ctx the parse tree
	 */
	void enterWhenSceneStarts(Scratch2Parser.WhenSceneStartsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#whenSceneStarts}.
	 * @param ctx the parse tree
	 */
	void exitWhenSceneStarts(Scratch2Parser.WhenSceneStartsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#whenSensorGreaterThan}.
	 * @param ctx the parse tree
	 */
	void enterWhenSensorGreaterThan(Scratch2Parser.WhenSensorGreaterThanContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#whenSensorGreaterThan}.
	 * @param ctx the parse tree
	 */
	void exitWhenSensorGreaterThan(Scratch2Parser.WhenSensorGreaterThanContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#whenIReceive}.
	 * @param ctx the parse tree
	 */
	void enterWhenIReceive(Scratch2Parser.WhenIReceiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#whenIReceive}.
	 * @param ctx the parse tree
	 */
	void exitWhenIReceive(Scratch2Parser.WhenIReceiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#whenCloned}.
	 * @param ctx the parse tree
	 */
	void enterWhenCloned(Scratch2Parser.WhenClonedContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#whenCloned}.
	 * @param ctx the parse tree
	 */
	void exitWhenCloned(Scratch2Parser.WhenClonedContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#stackBlock}.
	 * @param ctx the parse tree
	 */
	void enterStackBlock(Scratch2Parser.StackBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#stackBlock}.
	 * @param ctx the parse tree
	 */
	void exitStackBlock(Scratch2Parser.StackBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#forward}.
	 * @param ctx the parse tree
	 */
	void enterForward(Scratch2Parser.ForwardContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#forward}.
	 * @param ctx the parse tree
	 */
	void exitForward(Scratch2Parser.ForwardContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#turnRight}.
	 * @param ctx the parse tree
	 */
	void enterTurnRight(Scratch2Parser.TurnRightContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#turnRight}.
	 * @param ctx the parse tree
	 */
	void exitTurnRight(Scratch2Parser.TurnRightContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#turnLeft}.
	 * @param ctx the parse tree
	 */
	void enterTurnLeft(Scratch2Parser.TurnLeftContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#turnLeft}.
	 * @param ctx the parse tree
	 */
	void exitTurnLeft(Scratch2Parser.TurnLeftContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#heading}.
	 * @param ctx the parse tree
	 */
	void enterHeading(Scratch2Parser.HeadingContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#heading}.
	 * @param ctx the parse tree
	 */
	void exitHeading(Scratch2Parser.HeadingContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#pointTowards}.
	 * @param ctx the parse tree
	 */
	void enterPointTowards(Scratch2Parser.PointTowardsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#pointTowards}.
	 * @param ctx the parse tree
	 */
	void exitPointTowards(Scratch2Parser.PointTowardsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#goToXY}.
	 * @param ctx the parse tree
	 */
	void enterGoToXY(Scratch2Parser.GoToXYContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#goToXY}.
	 * @param ctx the parse tree
	 */
	void exitGoToXY(Scratch2Parser.GoToXYContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#goToSpriteOrMouse}.
	 * @param ctx the parse tree
	 */
	void enterGoToSpriteOrMouse(Scratch2Parser.GoToSpriteOrMouseContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#goToSpriteOrMouse}.
	 * @param ctx the parse tree
	 */
	void exitGoToSpriteOrMouse(Scratch2Parser.GoToSpriteOrMouseContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#glideSecToXYElapsedFrom}.
	 * @param ctx the parse tree
	 */
	void enterGlideSecToXYElapsedFrom(Scratch2Parser.GlideSecToXYElapsedFromContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#glideSecToXYElapsedFrom}.
	 * @param ctx the parse tree
	 */
	void exitGlideSecToXYElapsedFrom(Scratch2Parser.GlideSecToXYElapsedFromContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#changeXPosBy}.
	 * @param ctx the parse tree
	 */
	void enterChangeXPosBy(Scratch2Parser.ChangeXPosByContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#changeXPosBy}.
	 * @param ctx the parse tree
	 */
	void exitChangeXPosBy(Scratch2Parser.ChangeXPosByContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#xPos}.
	 * @param ctx the parse tree
	 */
	void enterXPos(Scratch2Parser.XPosContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#xPos}.
	 * @param ctx the parse tree
	 */
	void exitXPos(Scratch2Parser.XPosContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#changeYposBy}.
	 * @param ctx the parse tree
	 */
	void enterChangeYposBy(Scratch2Parser.ChangeYposByContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#changeYposBy}.
	 * @param ctx the parse tree
	 */
	void exitChangeYposBy(Scratch2Parser.ChangeYposByContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#yPos}.
	 * @param ctx the parse tree
	 */
	void enterYPos(Scratch2Parser.YPosContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#yPos}.
	 * @param ctx the parse tree
	 */
	void exitYPos(Scratch2Parser.YPosContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#bounceOffEdge}.
	 * @param ctx the parse tree
	 */
	void enterBounceOffEdge(Scratch2Parser.BounceOffEdgeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#bounceOffEdge}.
	 * @param ctx the parse tree
	 */
	void exitBounceOffEdge(Scratch2Parser.BounceOffEdgeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#setRotationStyle}.
	 * @param ctx the parse tree
	 */
	void enterSetRotationStyle(Scratch2Parser.SetRotationStyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#setRotationStyle}.
	 * @param ctx the parse tree
	 */
	void exitSetRotationStyle(Scratch2Parser.SetRotationStyleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#sayDurationElapsedFrom}.
	 * @param ctx the parse tree
	 */
	void enterSayDurationElapsedFrom(Scratch2Parser.SayDurationElapsedFromContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#sayDurationElapsedFrom}.
	 * @param ctx the parse tree
	 */
	void exitSayDurationElapsedFrom(Scratch2Parser.SayDurationElapsedFromContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#sayToken}.
	 * @param ctx the parse tree
	 */
	void enterSayToken(Scratch2Parser.SayTokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#sayToken}.
	 * @param ctx the parse tree
	 */
	void exitSayToken(Scratch2Parser.SayTokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#thinkDurationElapsedFrom}.
	 * @param ctx the parse tree
	 */
	void enterThinkDurationElapsedFrom(Scratch2Parser.ThinkDurationElapsedFromContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#thinkDurationElapsedFrom}.
	 * @param ctx the parse tree
	 */
	void exitThinkDurationElapsedFrom(Scratch2Parser.ThinkDurationElapsedFromContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#think}.
	 * @param ctx the parse tree
	 */
	void enterThink(Scratch2Parser.ThinkContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#think}.
	 * @param ctx the parse tree
	 */
	void exitThink(Scratch2Parser.ThinkContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#show}.
	 * @param ctx the parse tree
	 */
	void enterShow(Scratch2Parser.ShowContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#show}.
	 * @param ctx the parse tree
	 */
	void exitShow(Scratch2Parser.ShowContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#hide}.
	 * @param ctx the parse tree
	 */
	void enterHide(Scratch2Parser.HideContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#hide}.
	 * @param ctx the parse tree
	 */
	void exitHide(Scratch2Parser.HideContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#lookLike}.
	 * @param ctx the parse tree
	 */
	void enterLookLike(Scratch2Parser.LookLikeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#lookLike}.
	 * @param ctx the parse tree
	 */
	void exitLookLike(Scratch2Parser.LookLikeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#nextLook}.
	 * @param ctx the parse tree
	 */
	void enterNextLook(Scratch2Parser.NextLookContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#nextLook}.
	 * @param ctx the parse tree
	 */
	void exitNextLook(Scratch2Parser.NextLookContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#startScene}.
	 * @param ctx the parse tree
	 */
	void enterStartScene(Scratch2Parser.StartSceneContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#startScene}.
	 * @param ctx the parse tree
	 */
	void exitStartScene(Scratch2Parser.StartSceneContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#changeGraphicEffectBy}.
	 * @param ctx the parse tree
	 */
	void enterChangeGraphicEffectBy(Scratch2Parser.ChangeGraphicEffectByContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#changeGraphicEffectBy}.
	 * @param ctx the parse tree
	 */
	void exitChangeGraphicEffectBy(Scratch2Parser.ChangeGraphicEffectByContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#setGraphicEffect}.
	 * @param ctx the parse tree
	 */
	void enterSetGraphicEffect(Scratch2Parser.SetGraphicEffectContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#setGraphicEffect}.
	 * @param ctx the parse tree
	 */
	void exitSetGraphicEffect(Scratch2Parser.SetGraphicEffectContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#filterReset}.
	 * @param ctx the parse tree
	 */
	void enterFilterReset(Scratch2Parser.FilterResetContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#filterReset}.
	 * @param ctx the parse tree
	 */
	void exitFilterReset(Scratch2Parser.FilterResetContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#changeSizeBy}.
	 * @param ctx the parse tree
	 */
	void enterChangeSizeBy(Scratch2Parser.ChangeSizeByContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#changeSizeBy}.
	 * @param ctx the parse tree
	 */
	void exitChangeSizeBy(Scratch2Parser.ChangeSizeByContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#setSizeTo}.
	 * @param ctx the parse tree
	 */
	void enterSetSizeTo(Scratch2Parser.SetSizeToContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#setSizeTo}.
	 * @param ctx the parse tree
	 */
	void exitSetSizeTo(Scratch2Parser.SetSizeToContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#comeToFront}.
	 * @param ctx the parse tree
	 */
	void enterComeToFront(Scratch2Parser.ComeToFrontContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#comeToFront}.
	 * @param ctx the parse tree
	 */
	void exitComeToFront(Scratch2Parser.ComeToFrontContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#goBackByLayers}.
	 * @param ctx the parse tree
	 */
	void enterGoBackByLayers(Scratch2Parser.GoBackByLayersContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#goBackByLayers}.
	 * @param ctx the parse tree
	 */
	void exitGoBackByLayers(Scratch2Parser.GoBackByLayersContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#playSound}.
	 * @param ctx the parse tree
	 */
	void enterPlaySound(Scratch2Parser.PlaySoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#playSound}.
	 * @param ctx the parse tree
	 */
	void exitPlaySound(Scratch2Parser.PlaySoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#doPlaySoundAndWait}.
	 * @param ctx the parse tree
	 */
	void enterDoPlaySoundAndWait(Scratch2Parser.DoPlaySoundAndWaitContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#doPlaySoundAndWait}.
	 * @param ctx the parse tree
	 */
	void exitDoPlaySoundAndWait(Scratch2Parser.DoPlaySoundAndWaitContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#stopAllSounds}.
	 * @param ctx the parse tree
	 */
	void enterStopAllSounds(Scratch2Parser.StopAllSoundsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#stopAllSounds}.
	 * @param ctx the parse tree
	 */
	void exitStopAllSounds(Scratch2Parser.StopAllSoundsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#playDrum}.
	 * @param ctx the parse tree
	 */
	void enterPlayDrum(Scratch2Parser.PlayDrumContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#playDrum}.
	 * @param ctx the parse tree
	 */
	void exitPlayDrum(Scratch2Parser.PlayDrumContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#restElapsedFrom}.
	 * @param ctx the parse tree
	 */
	void enterRestElapsedFrom(Scratch2Parser.RestElapsedFromContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#restElapsedFrom}.
	 * @param ctx the parse tree
	 */
	void exitRestElapsedFrom(Scratch2Parser.RestElapsedFromContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#noteOnDurationElapsedFrom}.
	 * @param ctx the parse tree
	 */
	void enterNoteOnDurationElapsedFrom(Scratch2Parser.NoteOnDurationElapsedFromContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#noteOnDurationElapsedFrom}.
	 * @param ctx the parse tree
	 */
	void exitNoteOnDurationElapsedFrom(Scratch2Parser.NoteOnDurationElapsedFromContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#instrumentToken}.
	 * @param ctx the parse tree
	 */
	void enterInstrumentToken(Scratch2Parser.InstrumentTokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#instrumentToken}.
	 * @param ctx the parse tree
	 */
	void exitInstrumentToken(Scratch2Parser.InstrumentTokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#changeVolumeBy}.
	 * @param ctx the parse tree
	 */
	void enterChangeVolumeBy(Scratch2Parser.ChangeVolumeByContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#changeVolumeBy}.
	 * @param ctx the parse tree
	 */
	void exitChangeVolumeBy(Scratch2Parser.ChangeVolumeByContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#setVolumeTo}.
	 * @param ctx the parse tree
	 */
	void enterSetVolumeTo(Scratch2Parser.SetVolumeToContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#setVolumeTo}.
	 * @param ctx the parse tree
	 */
	void exitSetVolumeTo(Scratch2Parser.SetVolumeToContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#changeTempoBy}.
	 * @param ctx the parse tree
	 */
	void enterChangeTempoBy(Scratch2Parser.ChangeTempoByContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#changeTempoBy}.
	 * @param ctx the parse tree
	 */
	void exitChangeTempoBy(Scratch2Parser.ChangeTempoByContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#setTempoTo}.
	 * @param ctx the parse tree
	 */
	void enterSetTempoTo(Scratch2Parser.SetTempoToContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#setTempoTo}.
	 * @param ctx the parse tree
	 */
	void exitSetTempoTo(Scratch2Parser.SetTempoToContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#clearPenTrails}.
	 * @param ctx the parse tree
	 */
	void enterClearPenTrails(Scratch2Parser.ClearPenTrailsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#clearPenTrails}.
	 * @param ctx the parse tree
	 */
	void exitClearPenTrails(Scratch2Parser.ClearPenTrailsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#stampCostume}.
	 * @param ctx the parse tree
	 */
	void enterStampCostume(Scratch2Parser.StampCostumeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#stampCostume}.
	 * @param ctx the parse tree
	 */
	void exitStampCostume(Scratch2Parser.StampCostumeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#putPenDown}.
	 * @param ctx the parse tree
	 */
	void enterPutPenDown(Scratch2Parser.PutPenDownContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#putPenDown}.
	 * @param ctx the parse tree
	 */
	void exitPutPenDown(Scratch2Parser.PutPenDownContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#putPenUp}.
	 * @param ctx the parse tree
	 */
	void enterPutPenUp(Scratch2Parser.PutPenUpContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#putPenUp}.
	 * @param ctx the parse tree
	 */
	void exitPutPenUp(Scratch2Parser.PutPenUpContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#penColor}.
	 * @param ctx the parse tree
	 */
	void enterPenColor(Scratch2Parser.PenColorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#penColor}.
	 * @param ctx the parse tree
	 */
	void exitPenColor(Scratch2Parser.PenColorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#changePenHueBy}.
	 * @param ctx the parse tree
	 */
	void enterChangePenHueBy(Scratch2Parser.ChangePenHueByContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#changePenHueBy}.
	 * @param ctx the parse tree
	 */
	void exitChangePenHueBy(Scratch2Parser.ChangePenHueByContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#setPenHueTo}.
	 * @param ctx the parse tree
	 */
	void enterSetPenHueTo(Scratch2Parser.SetPenHueToContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#setPenHueTo}.
	 * @param ctx the parse tree
	 */
	void exitSetPenHueTo(Scratch2Parser.SetPenHueToContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#changePenShadeBy}.
	 * @param ctx the parse tree
	 */
	void enterChangePenShadeBy(Scratch2Parser.ChangePenShadeByContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#changePenShadeBy}.
	 * @param ctx the parse tree
	 */
	void exitChangePenShadeBy(Scratch2Parser.ChangePenShadeByContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#setPenShadeTo}.
	 * @param ctx the parse tree
	 */
	void enterSetPenShadeTo(Scratch2Parser.SetPenShadeToContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#setPenShadeTo}.
	 * @param ctx the parse tree
	 */
	void exitSetPenShadeTo(Scratch2Parser.SetPenShadeToContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#changePenSizeBy}.
	 * @param ctx the parse tree
	 */
	void enterChangePenSizeBy(Scratch2Parser.ChangePenSizeByContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#changePenSizeBy}.
	 * @param ctx the parse tree
	 */
	void exitChangePenSizeBy(Scratch2Parser.ChangePenSizeByContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#penSize}.
	 * @param ctx the parse tree
	 */
	void enterPenSize(Scratch2Parser.PenSizeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#penSize}.
	 * @param ctx the parse tree
	 */
	void exitPenSize(Scratch2Parser.PenSizeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#setVariableTo}.
	 * @param ctx the parse tree
	 */
	void enterSetVariableTo(Scratch2Parser.SetVariableToContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#setVariableTo}.
	 * @param ctx the parse tree
	 */
	void exitSetVariableTo(Scratch2Parser.SetVariableToContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#changeVariableBy}.
	 * @param ctx the parse tree
	 */
	void enterChangeVariableBy(Scratch2Parser.ChangeVariableByContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#changeVariableBy}.
	 * @param ctx the parse tree
	 */
	void exitChangeVariableBy(Scratch2Parser.ChangeVariableByContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#showVariable}.
	 * @param ctx the parse tree
	 */
	void enterShowVariable(Scratch2Parser.ShowVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#showVariable}.
	 * @param ctx the parse tree
	 */
	void exitShowVariable(Scratch2Parser.ShowVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#hideVariable}.
	 * @param ctx the parse tree
	 */
	void enterHideVariable(Scratch2Parser.HideVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#hideVariable}.
	 * @param ctx the parse tree
	 */
	void exitHideVariable(Scratch2Parser.HideVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#readVariable}.
	 * @param ctx the parse tree
	 */
	void enterReadVariable(Scratch2Parser.ReadVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#readVariable}.
	 * @param ctx the parse tree
	 */
	void exitReadVariable(Scratch2Parser.ReadVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#appendToList}.
	 * @param ctx the parse tree
	 */
	void enterAppendToList(Scratch2Parser.AppendToListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#appendToList}.
	 * @param ctx the parse tree
	 */
	void exitAppendToList(Scratch2Parser.AppendToListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#deleteLineOfList}.
	 * @param ctx the parse tree
	 */
	void enterDeleteLineOfList(Scratch2Parser.DeleteLineOfListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#deleteLineOfList}.
	 * @param ctx the parse tree
	 */
	void exitDeleteLineOfList(Scratch2Parser.DeleteLineOfListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#insertAtOfList}.
	 * @param ctx the parse tree
	 */
	void enterInsertAtOfList(Scratch2Parser.InsertAtOfListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#insertAtOfList}.
	 * @param ctx the parse tree
	 */
	void exitInsertAtOfList(Scratch2Parser.InsertAtOfListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#setLineOfListTo}.
	 * @param ctx the parse tree
	 */
	void enterSetLineOfListTo(Scratch2Parser.SetLineOfListToContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#setLineOfListTo}.
	 * @param ctx the parse tree
	 */
	void exitSetLineOfListTo(Scratch2Parser.SetLineOfListToContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#showList}.
	 * @param ctx the parse tree
	 */
	void enterShowList(Scratch2Parser.ShowListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#showList}.
	 * @param ctx the parse tree
	 */
	void exitShowList(Scratch2Parser.ShowListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#hideList}.
	 * @param ctx the parse tree
	 */
	void enterHideList(Scratch2Parser.HideListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#hideList}.
	 * @param ctx the parse tree
	 */
	void exitHideList(Scratch2Parser.HideListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#broadcast}.
	 * @param ctx the parse tree
	 */
	void enterBroadcast(Scratch2Parser.BroadcastContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#broadcast}.
	 * @param ctx the parse tree
	 */
	void exitBroadcast(Scratch2Parser.BroadcastContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#doBroadcastAndWait}.
	 * @param ctx the parse tree
	 */
	void enterDoBroadcastAndWait(Scratch2Parser.DoBroadcastAndWaitContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#doBroadcastAndWait}.
	 * @param ctx the parse tree
	 */
	void exitDoBroadcastAndWait(Scratch2Parser.DoBroadcastAndWaitContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#waitElapsedFrom}.
	 * @param ctx the parse tree
	 */
	void enterWaitElapsedFrom(Scratch2Parser.WaitElapsedFromContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#waitElapsedFrom}.
	 * @param ctx the parse tree
	 */
	void exitWaitElapsedFrom(Scratch2Parser.WaitElapsedFromContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#doWaitUntil}.
	 * @param ctx the parse tree
	 */
	void enterDoWaitUntil(Scratch2Parser.DoWaitUntilContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#doWaitUntil}.
	 * @param ctx the parse tree
	 */
	void exitDoWaitUntil(Scratch2Parser.DoWaitUntilContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#createCloneOf}.
	 * @param ctx the parse tree
	 */
	void enterCreateCloneOf(Scratch2Parser.CreateCloneOfContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#createCloneOf}.
	 * @param ctx the parse tree
	 */
	void exitCreateCloneOf(Scratch2Parser.CreateCloneOfContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#doAsk}.
	 * @param ctx the parse tree
	 */
	void enterDoAsk(Scratch2Parser.DoAskContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#doAsk}.
	 * @param ctx the parse tree
	 */
	void exitDoAsk(Scratch2Parser.DoAskContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#setVideoState}.
	 * @param ctx the parse tree
	 */
	void enterSetVideoState(Scratch2Parser.SetVideoStateContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#setVideoState}.
	 * @param ctx the parse tree
	 */
	void exitSetVideoState(Scratch2Parser.SetVideoStateContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#setVideoTransparency}.
	 * @param ctx the parse tree
	 */
	void enterSetVideoTransparency(Scratch2Parser.SetVideoTransparencyContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#setVideoTransparency}.
	 * @param ctx the parse tree
	 */
	void exitSetVideoTransparency(Scratch2Parser.SetVideoTransparencyContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#timerReset}.
	 * @param ctx the parse tree
	 */
	void enterTimerReset(Scratch2Parser.TimerResetContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#timerReset}.
	 * @param ctx the parse tree
	 */
	void exitTimerReset(Scratch2Parser.TimerResetContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#controlBlock}.
	 * @param ctx the parse tree
	 */
	void enterControlBlock(Scratch2Parser.ControlBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#controlBlock}.
	 * @param ctx the parse tree
	 */
	void exitControlBlock(Scratch2Parser.ControlBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#doRepeat}.
	 * @param ctx the parse tree
	 */
	void enterDoRepeat(Scratch2Parser.DoRepeatContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#doRepeat}.
	 * @param ctx the parse tree
	 */
	void exitDoRepeat(Scratch2Parser.DoRepeatContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#doForever}.
	 * @param ctx the parse tree
	 */
	void enterDoForever(Scratch2Parser.DoForeverContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#doForever}.
	 * @param ctx the parse tree
	 */
	void exitDoForever(Scratch2Parser.DoForeverContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#doIf}.
	 * @param ctx the parse tree
	 */
	void enterDoIf(Scratch2Parser.DoIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#doIf}.
	 * @param ctx the parse tree
	 */
	void exitDoIf(Scratch2Parser.DoIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#doIfElse}.
	 * @param ctx the parse tree
	 */
	void enterDoIfElse(Scratch2Parser.DoIfElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#doIfElse}.
	 * @param ctx the parse tree
	 */
	void exitDoIfElse(Scratch2Parser.DoIfElseContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#doUntil}.
	 * @param ctx the parse tree
	 */
	void enterDoUntil(Scratch2Parser.DoUntilContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#doUntil}.
	 * @param ctx the parse tree
	 */
	void exitDoUntil(Scratch2Parser.DoUntilContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#thenBody}.
	 * @param ctx the parse tree
	 */
	void enterThenBody(Scratch2Parser.ThenBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#thenBody}.
	 * @param ctx the parse tree
	 */
	void exitThenBody(Scratch2Parser.ThenBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#elseBody}.
	 * @param ctx the parse tree
	 */
	void enterElseBody(Scratch2Parser.ElseBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#elseBody}.
	 * @param ctx the parse tree
	 */
	void exitElseBody(Scratch2Parser.ElseBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#capBlock}.
	 * @param ctx the parse tree
	 */
	void enterCapBlock(Scratch2Parser.CapBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#capBlock}.
	 * @param ctx the parse tree
	 */
	void exitCapBlock(Scratch2Parser.CapBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#stopScripts}.
	 * @param ctx the parse tree
	 */
	void enterStopScripts(Scratch2Parser.StopScriptsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#stopScripts}.
	 * @param ctx the parse tree
	 */
	void exitStopScripts(Scratch2Parser.StopScriptsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#deleteClone}.
	 * @param ctx the parse tree
	 */
	void enterDeleteClone(Scratch2Parser.DeleteCloneContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#deleteClone}.
	 * @param ctx the parse tree
	 */
	void exitDeleteClone(Scratch2Parser.DeleteCloneContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#booleanBlock}.
	 * @param ctx the parse tree
	 */
	void enterBooleanBlock(Scratch2Parser.BooleanBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#booleanBlock}.
	 * @param ctx the parse tree
	 */
	void exitBooleanBlock(Scratch2Parser.BooleanBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#touching}.
	 * @param ctx the parse tree
	 */
	void enterTouching(Scratch2Parser.TouchingContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#touching}.
	 * @param ctx the parse tree
	 */
	void exitTouching(Scratch2Parser.TouchingContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#touchingColor}.
	 * @param ctx the parse tree
	 */
	void enterTouchingColor(Scratch2Parser.TouchingColorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#touchingColor}.
	 * @param ctx the parse tree
	 */
	void exitTouchingColor(Scratch2Parser.TouchingColorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#colorSees}.
	 * @param ctx the parse tree
	 */
	void enterColorSees(Scratch2Parser.ColorSeesContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#colorSees}.
	 * @param ctx the parse tree
	 */
	void exitColorSees(Scratch2Parser.ColorSeesContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#distanceTo}.
	 * @param ctx the parse tree
	 */
	void enterDistanceTo(Scratch2Parser.DistanceToContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#distanceTo}.
	 * @param ctx the parse tree
	 */
	void exitDistanceTo(Scratch2Parser.DistanceToContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#whenKeyPressedSensing}.
	 * @param ctx the parse tree
	 */
	void enterWhenKeyPressedSensing(Scratch2Parser.WhenKeyPressedSensingContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#whenKeyPressedSensing}.
	 * @param ctx the parse tree
	 */
	void exitWhenKeyPressedSensing(Scratch2Parser.WhenKeyPressedSensingContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#mousePressed}.
	 * @param ctx the parse tree
	 */
	void enterMousePressed(Scratch2Parser.MousePressedContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#mousePressed}.
	 * @param ctx the parse tree
	 */
	void exitMousePressed(Scratch2Parser.MousePressedContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#listContains}.
	 * @param ctx the parse tree
	 */
	void enterListContains(Scratch2Parser.ListContainsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#listContains}.
	 * @param ctx the parse tree
	 */
	void exitListContains(Scratch2Parser.ListContainsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#lower}.
	 * @param ctx the parse tree
	 */
	void enterLower(Scratch2Parser.LowerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#lower}.
	 * @param ctx the parse tree
	 */
	void exitLower(Scratch2Parser.LowerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#equal}.
	 * @param ctx the parse tree
	 */
	void enterEqual(Scratch2Parser.EqualContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#equal}.
	 * @param ctx the parse tree
	 */
	void exitEqual(Scratch2Parser.EqualContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#higher}.
	 * @param ctx the parse tree
	 */
	void enterHigher(Scratch2Parser.HigherContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#higher}.
	 * @param ctx the parse tree
	 */
	void exitHigher(Scratch2Parser.HigherContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#and}.
	 * @param ctx the parse tree
	 */
	void enterAnd(Scratch2Parser.AndContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#and}.
	 * @param ctx the parse tree
	 */
	void exitAnd(Scratch2Parser.AndContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#or}.
	 * @param ctx the parse tree
	 */
	void enterOr(Scratch2Parser.OrContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#or}.
	 * @param ctx the parse tree
	 */
	void exitOr(Scratch2Parser.OrContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#not}.
	 * @param ctx the parse tree
	 */
	void enterNot(Scratch2Parser.NotContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#not}.
	 * @param ctx the parse tree
	 */
	void exitNot(Scratch2Parser.NotContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#leftOperand}.
	 * @param ctx the parse tree
	 */
	void enterLeftOperand(Scratch2Parser.LeftOperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#leftOperand}.
	 * @param ctx the parse tree
	 */
	void exitLeftOperand(Scratch2Parser.LeftOperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#centerOperand}.
	 * @param ctx the parse tree
	 */
	void enterCenterOperand(Scratch2Parser.CenterOperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#centerOperand}.
	 * @param ctx the parse tree
	 */
	void exitCenterOperand(Scratch2Parser.CenterOperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#rightOperand}.
	 * @param ctx the parse tree
	 */
	void enterRightOperand(Scratch2Parser.RightOperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#rightOperand}.
	 * @param ctx the parse tree
	 */
	void exitRightOperand(Scratch2Parser.RightOperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#singleOperand}.
	 * @param ctx the parse tree
	 */
	void enterSingleOperand(Scratch2Parser.SingleOperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#singleOperand}.
	 * @param ctx the parse tree
	 */
	void exitSingleOperand(Scratch2Parser.SingleOperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#reporterBlock}.
	 * @param ctx the parse tree
	 */
	void enterReporterBlock(Scratch2Parser.ReporterBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#reporterBlock}.
	 * @param ctx the parse tree
	 */
	void exitReporterBlock(Scratch2Parser.ReporterBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#yPosSingle}.
	 * @param ctx the parse tree
	 */
	void enterYPosSingle(Scratch2Parser.YPosSingleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#yPosSingle}.
	 * @param ctx the parse tree
	 */
	void exitYPosSingle(Scratch2Parser.YPosSingleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#xPosSingle}.
	 * @param ctx the parse tree
	 */
	void enterXPosSingle(Scratch2Parser.XPosSingleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#xPosSingle}.
	 * @param ctx the parse tree
	 */
	void exitXPosSingle(Scratch2Parser.XPosSingleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#headingSingle}.
	 * @param ctx the parse tree
	 */
	void enterHeadingSingle(Scratch2Parser.HeadingSingleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#headingSingle}.
	 * @param ctx the parse tree
	 */
	void exitHeadingSingle(Scratch2Parser.HeadingSingleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#costumeIndex}.
	 * @param ctx the parse tree
	 */
	void enterCostumeIndex(Scratch2Parser.CostumeIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#costumeIndex}.
	 * @param ctx the parse tree
	 */
	void exitCostumeIndex(Scratch2Parser.CostumeIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#sceneName}.
	 * @param ctx the parse tree
	 */
	void enterSceneName(Scratch2Parser.SceneNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#sceneName}.
	 * @param ctx the parse tree
	 */
	void exitSceneName(Scratch2Parser.SceneNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#scaleReporter}.
	 * @param ctx the parse tree
	 */
	void enterScaleReporter(Scratch2Parser.ScaleReporterContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#scaleReporter}.
	 * @param ctx the parse tree
	 */
	void exitScaleReporter(Scratch2Parser.ScaleReporterContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#volume}.
	 * @param ctx the parse tree
	 */
	void enterVolume(Scratch2Parser.VolumeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#volume}.
	 * @param ctx the parse tree
	 */
	void exitVolume(Scratch2Parser.VolumeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#tempo}.
	 * @param ctx the parse tree
	 */
	void enterTempo(Scratch2Parser.TempoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#tempo}.
	 * @param ctx the parse tree
	 */
	void exitTempo(Scratch2Parser.TempoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#getLineOfList}.
	 * @param ctx the parse tree
	 */
	void enterGetLineOfList(Scratch2Parser.GetLineOfListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#getLineOfList}.
	 * @param ctx the parse tree
	 */
	void exitGetLineOfList(Scratch2Parser.GetLineOfListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#lineCountOfList}.
	 * @param ctx the parse tree
	 */
	void enterLineCountOfList(Scratch2Parser.LineCountOfListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#lineCountOfList}.
	 * @param ctx the parse tree
	 */
	void exitLineCountOfList(Scratch2Parser.LineCountOfListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#contentsOfList}.
	 * @param ctx the parse tree
	 */
	void enterContentsOfList(Scratch2Parser.ContentsOfListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#contentsOfList}.
	 * @param ctx the parse tree
	 */
	void exitContentsOfList(Scratch2Parser.ContentsOfListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#answer}.
	 * @param ctx the parse tree
	 */
	void enterAnswer(Scratch2Parser.AnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#answer}.
	 * @param ctx the parse tree
	 */
	void exitAnswer(Scratch2Parser.AnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#mouseX}.
	 * @param ctx the parse tree
	 */
	void enterMouseX(Scratch2Parser.MouseXContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#mouseX}.
	 * @param ctx the parse tree
	 */
	void exitMouseX(Scratch2Parser.MouseXContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#mouseY}.
	 * @param ctx the parse tree
	 */
	void enterMouseY(Scratch2Parser.MouseYContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#mouseY}.
	 * @param ctx the parse tree
	 */
	void exitMouseY(Scratch2Parser.MouseYContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#soundLevel}.
	 * @param ctx the parse tree
	 */
	void enterSoundLevel(Scratch2Parser.SoundLevelContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#soundLevel}.
	 * @param ctx the parse tree
	 */
	void exitSoundLevel(Scratch2Parser.SoundLevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#senseVideoMotion}.
	 * @param ctx the parse tree
	 */
	void enterSenseVideoMotion(Scratch2Parser.SenseVideoMotionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#senseVideoMotion}.
	 * @param ctx the parse tree
	 */
	void exitSenseVideoMotion(Scratch2Parser.SenseVideoMotionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#timer}.
	 * @param ctx the parse tree
	 */
	void enterTimer(Scratch2Parser.TimerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#timer}.
	 * @param ctx the parse tree
	 */
	void exitTimer(Scratch2Parser.TimerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#getAttributeOf}.
	 * @param ctx the parse tree
	 */
	void enterGetAttributeOf(Scratch2Parser.GetAttributeOfContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#getAttributeOf}.
	 * @param ctx the parse tree
	 */
	void exitGetAttributeOf(Scratch2Parser.GetAttributeOfContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#timeAndDate}.
	 * @param ctx the parse tree
	 */
	void enterTimeAndDate(Scratch2Parser.TimeAndDateContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#timeAndDate}.
	 * @param ctx the parse tree
	 */
	void exitTimeAndDate(Scratch2Parser.TimeAndDateContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#timestamp}.
	 * @param ctx the parse tree
	 */
	void enterTimestamp(Scratch2Parser.TimestampContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#timestamp}.
	 * @param ctx the parse tree
	 */
	void exitTimestamp(Scratch2Parser.TimestampContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#getUserName}.
	 * @param ctx the parse tree
	 */
	void enterGetUserName(Scratch2Parser.GetUserNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#getUserName}.
	 * @param ctx the parse tree
	 */
	void exitGetUserName(Scratch2Parser.GetUserNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#plus}.
	 * @param ctx the parse tree
	 */
	void enterPlus(Scratch2Parser.PlusContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#plus}.
	 * @param ctx the parse tree
	 */
	void exitPlus(Scratch2Parser.PlusContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#minus}.
	 * @param ctx the parse tree
	 */
	void enterMinus(Scratch2Parser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#minus}.
	 * @param ctx the parse tree
	 */
	void exitMinus(Scratch2Parser.MinusContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#multiply}.
	 * @param ctx the parse tree
	 */
	void enterMultiply(Scratch2Parser.MultiplyContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#multiply}.
	 * @param ctx the parse tree
	 */
	void exitMultiply(Scratch2Parser.MultiplyContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#divider}.
	 * @param ctx the parse tree
	 */
	void enterDivider(Scratch2Parser.DividerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#divider}.
	 * @param ctx the parse tree
	 */
	void exitDivider(Scratch2Parser.DividerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#randomFromTo}.
	 * @param ctx the parse tree
	 */
	void enterRandomFromTo(Scratch2Parser.RandomFromToContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#randomFromTo}.
	 * @param ctx the parse tree
	 */
	void exitRandomFromTo(Scratch2Parser.RandomFromToContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#concatenateWith}.
	 * @param ctx the parse tree
	 */
	void enterConcatenateWith(Scratch2Parser.ConcatenateWithContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#concatenateWith}.
	 * @param ctx the parse tree
	 */
	void exitConcatenateWith(Scratch2Parser.ConcatenateWithContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#letterOf}.
	 * @param ctx the parse tree
	 */
	void enterLetterOf(Scratch2Parser.LetterOfContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#letterOf}.
	 * @param ctx the parse tree
	 */
	void exitLetterOf(Scratch2Parser.LetterOfContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#stringLength}.
	 * @param ctx the parse tree
	 */
	void enterStringLength(Scratch2Parser.StringLengthContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#stringLength}.
	 * @param ctx the parse tree
	 */
	void exitStringLength(Scratch2Parser.StringLengthContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#mod}.
	 * @param ctx the parse tree
	 */
	void enterMod(Scratch2Parser.ModContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#mod}.
	 * @param ctx the parse tree
	 */
	void exitMod(Scratch2Parser.ModContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#rounded}.
	 * @param ctx the parse tree
	 */
	void enterRounded(Scratch2Parser.RoundedContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#rounded}.
	 * @param ctx the parse tree
	 */
	void exitRounded(Scratch2Parser.RoundedContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#computeFunctionOf}.
	 * @param ctx the parse tree
	 */
	void enterComputeFunctionOf(Scratch2Parser.ComputeFunctionOfContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#computeFunctionOf}.
	 * @param ctx the parse tree
	 */
	void exitComputeFunctionOf(Scratch2Parser.ComputeFunctionOfContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#variables}.
	 * @param ctx the parse tree
	 */
	void enterVariables(Scratch2Parser.VariablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#variables}.
	 * @param ctx the parse tree
	 */
	void exitVariables(Scratch2Parser.VariablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#variableArray}.
	 * @param ctx the parse tree
	 */
	void enterVariableArray(Scratch2Parser.VariableArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#variableArray}.
	 * @param ctx the parse tree
	 */
	void exitVariableArray(Scratch2Parser.VariableArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#variableBlock}.
	 * @param ctx the parse tree
	 */
	void enterVariableBlock(Scratch2Parser.VariableBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#variableBlock}.
	 * @param ctx the parse tree
	 */
	void exitVariableBlock(Scratch2Parser.VariableBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(Scratch2Parser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(Scratch2Parser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#variableValue}.
	 * @param ctx the parse tree
	 */
	void enterVariableValue(Scratch2Parser.VariableValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#variableValue}.
	 * @param ctx the parse tree
	 */
	void exitVariableValue(Scratch2Parser.VariableValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#isPersistent}.
	 * @param ctx the parse tree
	 */
	void enterIsPersistent(Scratch2Parser.IsPersistentContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#isPersistent}.
	 * @param ctx the parse tree
	 */
	void exitIsPersistent(Scratch2Parser.IsPersistentContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(Scratch2Parser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(Scratch2Parser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#listArray}.
	 * @param ctx the parse tree
	 */
	void enterListArray(Scratch2Parser.ListArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#listArray}.
	 * @param ctx the parse tree
	 */
	void exitListArray(Scratch2Parser.ListArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#listBlock}.
	 * @param ctx the parse tree
	 */
	void enterListBlock(Scratch2Parser.ListBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#listBlock}.
	 * @param ctx the parse tree
	 */
	void exitListBlock(Scratch2Parser.ListBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#listName}.
	 * @param ctx the parse tree
	 */
	void enterListName(Scratch2Parser.ListNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#listName}.
	 * @param ctx the parse tree
	 */
	void exitListName(Scratch2Parser.ListNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#contents}.
	 * @param ctx the parse tree
	 */
	void enterContents(Scratch2Parser.ContentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#contents}.
	 * @param ctx the parse tree
	 */
	void exitContents(Scratch2Parser.ContentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#x}.
	 * @param ctx the parse tree
	 */
	void enterX(Scratch2Parser.XContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#x}.
	 * @param ctx the parse tree
	 */
	void exitX(Scratch2Parser.XContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#y}.
	 * @param ctx the parse tree
	 */
	void enterY(Scratch2Parser.YContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#y}.
	 * @param ctx the parse tree
	 */
	void exitY(Scratch2Parser.YContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#width}.
	 * @param ctx the parse tree
	 */
	void enterWidth(Scratch2Parser.WidthContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#width}.
	 * @param ctx the parse tree
	 */
	void exitWidth(Scratch2Parser.WidthContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#height}.
	 * @param ctx the parse tree
	 */
	void enterHeight(Scratch2Parser.HeightContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#height}.
	 * @param ctx the parse tree
	 */
	void exitHeight(Scratch2Parser.HeightContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#sounds}.
	 * @param ctx the parse tree
	 */
	void enterSounds(Scratch2Parser.SoundsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#sounds}.
	 * @param ctx the parse tree
	 */
	void exitSounds(Scratch2Parser.SoundsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#soundsArray}.
	 * @param ctx the parse tree
	 */
	void enterSoundsArray(Scratch2Parser.SoundsArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#soundsArray}.
	 * @param ctx the parse tree
	 */
	void exitSoundsArray(Scratch2Parser.SoundsArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#sounds_block}.
	 * @param ctx the parse tree
	 */
	void enterSounds_block(Scratch2Parser.Sounds_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#sounds_block}.
	 * @param ctx the parse tree
	 */
	void exitSounds_block(Scratch2Parser.Sounds_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#sound_name}.
	 * @param ctx the parse tree
	 */
	void enterSound_name(Scratch2Parser.Sound_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#sound_name}.
	 * @param ctx the parse tree
	 */
	void exitSound_name(Scratch2Parser.Sound_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#soundID}.
	 * @param ctx the parse tree
	 */
	void enterSoundID(Scratch2Parser.SoundIDContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#soundID}.
	 * @param ctx the parse tree
	 */
	void exitSoundID(Scratch2Parser.SoundIDContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#md5}.
	 * @param ctx the parse tree
	 */
	void enterMd5(Scratch2Parser.Md5Context ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#md5}.
	 * @param ctx the parse tree
	 */
	void exitMd5(Scratch2Parser.Md5Context ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#sampleCount}.
	 * @param ctx the parse tree
	 */
	void enterSampleCount(Scratch2Parser.SampleCountContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#sampleCount}.
	 * @param ctx the parse tree
	 */
	void exitSampleCount(Scratch2Parser.SampleCountContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#rate}.
	 * @param ctx the parse tree
	 */
	void enterRate(Scratch2Parser.RateContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#rate}.
	 * @param ctx the parse tree
	 */
	void exitRate(Scratch2Parser.RateContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#format}.
	 * @param ctx the parse tree
	 */
	void enterFormat(Scratch2Parser.FormatContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#format}.
	 * @param ctx the parse tree
	 */
	void exitFormat(Scratch2Parser.FormatContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#scale}.
	 * @param ctx the parse tree
	 */
	void enterScale(Scratch2Parser.ScaleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#scale}.
	 * @param ctx the parse tree
	 */
	void exitScale(Scratch2Parser.ScaleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#costumes}.
	 * @param ctx the parse tree
	 */
	void enterCostumes(Scratch2Parser.CostumesContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#costumes}.
	 * @param ctx the parse tree
	 */
	void exitCostumes(Scratch2Parser.CostumesContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#costumesArray}.
	 * @param ctx the parse tree
	 */
	void enterCostumesArray(Scratch2Parser.CostumesArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#costumesArray}.
	 * @param ctx the parse tree
	 */
	void exitCostumesArray(Scratch2Parser.CostumesArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#costume_block}.
	 * @param ctx the parse tree
	 */
	void enterCostume_block(Scratch2Parser.Costume_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#costume_block}.
	 * @param ctx the parse tree
	 */
	void exitCostume_block(Scratch2Parser.Costume_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#costumeName}.
	 * @param ctx the parse tree
	 */
	void enterCostumeName(Scratch2Parser.CostumeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#costumeName}.
	 * @param ctx the parse tree
	 */
	void exitCostumeName(Scratch2Parser.CostumeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#baseLayerID}.
	 * @param ctx the parse tree
	 */
	void enterBaseLayerID(Scratch2Parser.BaseLayerIDContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#baseLayerID}.
	 * @param ctx the parse tree
	 */
	void exitBaseLayerID(Scratch2Parser.BaseLayerIDContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#baseLayerMD5}.
	 * @param ctx the parse tree
	 */
	void enterBaseLayerMD5(Scratch2Parser.BaseLayerMD5Context ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#baseLayerMD5}.
	 * @param ctx the parse tree
	 */
	void exitBaseLayerMD5(Scratch2Parser.BaseLayerMD5Context ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#bitmapResolution}.
	 * @param ctx the parse tree
	 */
	void enterBitmapResolution(Scratch2Parser.BitmapResolutionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#bitmapResolution}.
	 * @param ctx the parse tree
	 */
	void exitBitmapResolution(Scratch2Parser.BitmapResolutionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#rotationCenterX}.
	 * @param ctx the parse tree
	 */
	void enterRotationCenterX(Scratch2Parser.RotationCenterXContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#rotationCenterX}.
	 * @param ctx the parse tree
	 */
	void exitRotationCenterX(Scratch2Parser.RotationCenterXContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#rotationCenterY}.
	 * @param ctx the parse tree
	 */
	void enterRotationCenterY(Scratch2Parser.RotationCenterYContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#rotationCenterY}.
	 * @param ctx the parse tree
	 */
	void exitRotationCenterY(Scratch2Parser.RotationCenterYContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#currentCostumeIndex}.
	 * @param ctx the parse tree
	 */
	void enterCurrentCostumeIndex(Scratch2Parser.CurrentCostumeIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#currentCostumeIndex}.
	 * @param ctx the parse tree
	 */
	void exitCurrentCostumeIndex(Scratch2Parser.CurrentCostumeIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#penLayerMD5}.
	 * @param ctx the parse tree
	 */
	void enterPenLayerMD5(Scratch2Parser.PenLayerMD5Context ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#penLayerMD5}.
	 * @param ctx the parse tree
	 */
	void exitPenLayerMD5(Scratch2Parser.PenLayerMD5Context ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#penLayerID}.
	 * @param ctx the parse tree
	 */
	void enterPenLayerID(Scratch2Parser.PenLayerIDContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#penLayerID}.
	 * @param ctx the parse tree
	 */
	void exitPenLayerID(Scratch2Parser.PenLayerIDContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#tempoBPM}.
	 * @param ctx the parse tree
	 */
	void enterTempoBPM(Scratch2Parser.TempoBPMContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#tempoBPM}.
	 * @param ctx the parse tree
	 */
	void exitTempoBPM(Scratch2Parser.TempoBPMContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#videoAlpha}.
	 * @param ctx the parse tree
	 */
	void enterVideoAlpha(Scratch2Parser.VideoAlphaContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#videoAlpha}.
	 * @param ctx the parse tree
	 */
	void exitVideoAlpha(Scratch2Parser.VideoAlphaContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#children}.
	 * @param ctx the parse tree
	 */
	void enterChildren(Scratch2Parser.ChildrenContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#children}.
	 * @param ctx the parse tree
	 */
	void exitChildren(Scratch2Parser.ChildrenContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#children_blocks}.
	 * @param ctx the parse tree
	 */
	void enterChildren_blocks(Scratch2Parser.Children_blocksContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#children_blocks}.
	 * @param ctx the parse tree
	 */
	void exitChildren_blocks(Scratch2Parser.Children_blocksContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#scratchX}.
	 * @param ctx the parse tree
	 */
	void enterScratchX(Scratch2Parser.ScratchXContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#scratchX}.
	 * @param ctx the parse tree
	 */
	void exitScratchX(Scratch2Parser.ScratchXContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#scratchY}.
	 * @param ctx the parse tree
	 */
	void enterScratchY(Scratch2Parser.ScratchYContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#scratchY}.
	 * @param ctx the parse tree
	 */
	void exitScratchY(Scratch2Parser.ScratchYContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#direction}.
	 * @param ctx the parse tree
	 */
	void enterDirection(Scratch2Parser.DirectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#direction}.
	 * @param ctx the parse tree
	 */
	void exitDirection(Scratch2Parser.DirectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#rotationStyle}.
	 * @param ctx the parse tree
	 */
	void enterRotationStyle(Scratch2Parser.RotationStyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#rotationStyle}.
	 * @param ctx the parse tree
	 */
	void exitRotationStyle(Scratch2Parser.RotationStyleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#isDraggable}.
	 * @param ctx the parse tree
	 */
	void enterIsDraggable(Scratch2Parser.IsDraggableContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#isDraggable}.
	 * @param ctx the parse tree
	 */
	void exitIsDraggable(Scratch2Parser.IsDraggableContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#indexInLibrary}.
	 * @param ctx the parse tree
	 */
	void enterIndexInLibrary(Scratch2Parser.IndexInLibraryContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#indexInLibrary}.
	 * @param ctx the parse tree
	 */
	void exitIndexInLibrary(Scratch2Parser.IndexInLibraryContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#visible}.
	 * @param ctx the parse tree
	 */
	void enterVisible(Scratch2Parser.VisibleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#visible}.
	 * @param ctx the parse tree
	 */
	void exitVisible(Scratch2Parser.VisibleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#spriteInfo}.
	 * @param ctx the parse tree
	 */
	void enterSpriteInfo(Scratch2Parser.SpriteInfoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#spriteInfo}.
	 * @param ctx the parse tree
	 */
	void exitSpriteInfo(Scratch2Parser.SpriteInfoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#info}.
	 * @param ctx the parse tree
	 */
	void enterInfo(Scratch2Parser.InfoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#info}.
	 * @param ctx the parse tree
	 */
	void exitInfo(Scratch2Parser.InfoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#variableDisplay}.
	 * @param ctx the parse tree
	 */
	void enterVariableDisplay(Scratch2Parser.VariableDisplayContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#variableDisplay}.
	 * @param ctx the parse tree
	 */
	void exitVariableDisplay(Scratch2Parser.VariableDisplayContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#target}.
	 * @param ctx the parse tree
	 */
	void enterTarget(Scratch2Parser.TargetContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#target}.
	 * @param ctx the parse tree
	 */
	void exitTarget(Scratch2Parser.TargetContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(Scratch2Parser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(Scratch2Parser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(Scratch2Parser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(Scratch2Parser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#color}.
	 * @param ctx the parse tree
	 */
	void enterColor(Scratch2Parser.ColorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#color}.
	 * @param ctx the parse tree
	 */
	void exitColor(Scratch2Parser.ColorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(Scratch2Parser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(Scratch2Parser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#modeDisplay}.
	 * @param ctx the parse tree
	 */
	void enterModeDisplay(Scratch2Parser.ModeDisplayContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#modeDisplay}.
	 * @param ctx the parse tree
	 */
	void exitModeDisplay(Scratch2Parser.ModeDisplayContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#sliderMin}.
	 * @param ctx the parse tree
	 */
	void enterSliderMin(Scratch2Parser.SliderMinContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#sliderMin}.
	 * @param ctx the parse tree
	 */
	void exitSliderMin(Scratch2Parser.SliderMinContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#sliderMax}.
	 * @param ctx the parse tree
	 */
	void enterSliderMax(Scratch2Parser.SliderMaxContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#sliderMax}.
	 * @param ctx the parse tree
	 */
	void exitSliderMax(Scratch2Parser.SliderMaxContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#isDiscrete}.
	 * @param ctx the parse tree
	 */
	void enterIsDiscrete(Scratch2Parser.IsDiscreteContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#isDiscrete}.
	 * @param ctx the parse tree
	 */
	void exitIsDiscrete(Scratch2Parser.IsDiscreteContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#infoArray}.
	 * @param ctx the parse tree
	 */
	void enterInfoArray(Scratch2Parser.InfoArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#infoArray}.
	 * @param ctx the parse tree
	 */
	void exitInfoArray(Scratch2Parser.InfoArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#flashVersion}.
	 * @param ctx the parse tree
	 */
	void enterFlashVersion(Scratch2Parser.FlashVersionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#flashVersion}.
	 * @param ctx the parse tree
	 */
	void exitFlashVersion(Scratch2Parser.FlashVersionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#projectID}.
	 * @param ctx the parse tree
	 */
	void enterProjectID(Scratch2Parser.ProjectIDContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#projectID}.
	 * @param ctx the parse tree
	 */
	void exitProjectID(Scratch2Parser.ProjectIDContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#hasCloudData}.
	 * @param ctx the parse tree
	 */
	void enterHasCloudData(Scratch2Parser.HasCloudDataContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#hasCloudData}.
	 * @param ctx the parse tree
	 */
	void exitHasCloudData(Scratch2Parser.HasCloudDataContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#spriteCount}.
	 * @param ctx the parse tree
	 */
	void enterSpriteCount(Scratch2Parser.SpriteCountContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#spriteCount}.
	 * @param ctx the parse tree
	 */
	void exitSpriteCount(Scratch2Parser.SpriteCountContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#videoOn}.
	 * @param ctx the parse tree
	 */
	void enterVideoOn(Scratch2Parser.VideoOnContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#videoOn}.
	 * @param ctx the parse tree
	 */
	void exitVideoOn(Scratch2Parser.VideoOnContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#swfVersion}.
	 * @param ctx the parse tree
	 */
	void enterSwfVersion(Scratch2Parser.SwfVersionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#swfVersion}.
	 * @param ctx the parse tree
	 */
	void exitSwfVersion(Scratch2Parser.SwfVersionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#userAgent}.
	 * @param ctx the parse tree
	 */
	void enterUserAgent(Scratch2Parser.UserAgentContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#userAgent}.
	 * @param ctx the parse tree
	 */
	void exitUserAgent(Scratch2Parser.UserAgentContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#scriptCount}.
	 * @param ctx the parse tree
	 */
	void enterScriptCount(Scratch2Parser.ScriptCountContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#scriptCount}.
	 * @param ctx the parse tree
	 */
	void exitScriptCount(Scratch2Parser.ScriptCountContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#literalString}.
	 * @param ctx the parse tree
	 */
	void enterLiteralString(Scratch2Parser.LiteralStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#literalString}.
	 * @param ctx the parse tree
	 */
	void exitLiteralString(Scratch2Parser.LiteralStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#literalBoolean}.
	 * @param ctx the parse tree
	 */
	void enterLiteralBoolean(Scratch2Parser.LiteralBooleanContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#literalBoolean}.
	 * @param ctx the parse tree
	 */
	void exitLiteralBoolean(Scratch2Parser.LiteralBooleanContext ctx);
	/**
	 * Enter a parse tree produced by {@link Scratch2Parser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(Scratch2Parser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link Scratch2Parser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(Scratch2Parser.ValueContext ctx);
}