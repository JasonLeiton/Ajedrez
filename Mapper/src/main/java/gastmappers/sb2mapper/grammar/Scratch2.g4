
grammar Scratch2;

root
   : spriteObject
   ;

/**------------------------------------ BEGIN Variable Display ------------------------------------------*/



/**------------------------------------ BEGIN SPRITE OBJECT ------------------------------------------*/
spriteObject
   : '{' (scripts | variables | list | sounds | objName | scale | costumes | currentCostumeIndex | penLayerMD5 | penLayerID | tempoBPM | videoAlpha | children | scratchX | scratchY | direction | rotationStyle
               | isDraggable | indexInLibrary | visible | spriteInfo | info)* ( (',' scripts)
      | (',' variables)
      | (',' list)
      | (',' sounds)
      | (',' objName)
      | (',' scale)
      | (',' currentCostumeIndex)
      | (',' penLayerMD5)
      | (',' penLayerID)
      | (',' tempoBPM)
      | (',' videoAlpha)
      | (',' costumes)
      | (',' children)
      | (',' scratchX)
      | (',' scratchY)
      | (',' direction)
      | (',' rotationStyle)
      | (',' isDraggable)
      | (',' indexInLibrary)
      | (',' visible)
      | (',' spriteInfo)
      | (',' info))*  '}'
   | '{' '}'
   ;

objName
   : OBJ_NAME_TOKEN ':' (literalString)
   ;

scripts
   : SCRIPTS_TOKEN ':' script_blocks;

/**------------------------------------ BEGIN SCRIPT BLOCKS ------------------------------------------*/
script_blocks
   :'[' script_block (',' script_block)* ']'
   ;

script_block
   :'[' position ',' code ']';

position
   :NUMBER ',' NUMBER;

code
   : statementArray;

statementArray
   :
   | '[' statement (',' statement)* ']'
   | '[' ']'
   ;

statement
   : hatBlock
   | stackBlock
   | capBlock
   | controlBlock
   | booleanBlock
   | reporterBlock
   | functionBlock
   ;
/**------------------------------------ FUNCTION BLOCK ------------------------------------------*/

functionBlock
    : procDef
    | call
    ;

procDef
    : '[' PROC_DEF_TOKEN  ',' name_function ',' param_type_definition ',' param_init_values ',' literalBoolean ']'
    ;

name_function
    : literalString
    ;

param_type_definition
    : '[' literalString (',' literalString)* ']'
    ;

param_init_values
      : '[' ( NUMBER | literalBoolean | literalString )* ((',' NUMBER)
      | (',' literalBoolean)
      | (',' literalString))*
      ']'
    ;

PROC_DEF_TOKEN
    : '"' 'procDef' '"'
    ;

call
    : '[' CALL_TOKEN ',' name_function ',' params_value ']'
    ;

params_value
    : (NUMBER | literalBoolean | literalString | statement )* ((',' NUMBER)
    | (',' literalBoolean)
    | (',' literalString)
    | (',' statement))*
    ;
CALL_TOKEN
    : '"' 'call' '"'
    ;
/**------------------------------------ HAT BLOCKS ------------------------------------------*/
hatBlock
   /**-------------EVENTS----------------*/
   : whenGreenFlag
   | whenKeyPressed
   | whenClicked
   | whenSceneStarts
   | whenSensorGreaterThan
   | whenIReceive

   /**-------------CONTROL----------------*/
   |  whenCloned
   ;

   whenGreenFlag
      : '[' WHEN_GREEN_FLAG_TOKEN ']';

   whenKeyPressed
      : '[' WHEN_KEY_PRESSED_TOKEN  ',' literalString ']';

   whenClicked
      : '[' WHEN_CLICKED_TOKEN ']';

   whenSceneStarts
      : '[' WHEN_SCENE_STARTS_TOKEN  ',' literalString  ']';

   whenSensorGreaterThan
      : '[' WHEN_SENSOR_GREATER_THAN_TOKEN  ',' literalString ',' NUMBER ']'
      | '[' WHEN_SENSOR_GREATER_THAN_TOKEN  ',' literalString ',' statement ']';

   whenIReceive
      : '[' WHEN_I_RECEIVE_TOKEN  ',' literalString  ']';

   whenCloned
      : '[' WHEN_CLONED_TOKEN ']';

/**------------------------------------ STACK BLOCKS ------------------------------------------*/
stackBlock
   /**-------------MOTION----------------*/
   : forward
   | turnRight
   | turnLeft
   | heading
   | pointTowards
   | goToXY
   | goToSpriteOrMouse
   | glideSecToXYElapsedFrom
   | changeXPosBy
   | xPos
   | changeYposBy
   | yPos
   | bounceOffEdge
   | setRotationStyle
   /**-------------LOOKS----------------*/
   | sayDurationElapsedFrom
   | sayToken
   | thinkDurationElapsedFrom
   | think
   | show
   | hide
   | lookLike
   | nextLook
   | startScene
   | changeGraphicEffectBy
   | setGraphicEffect
   | filterReset
   | changeSizeBy
   | setSizeTo
   | comeToFront
   | goBackByLayers
   /**-------------SOUND----------------*/
   | playSound
   | doPlaySoundAndWait
   | stopAllSounds
   | playDrum
   | restElapsedFrom
   | noteOnDurationElapsedFrom
   | instrumentToken
   | changeVolumeBy
   | setVolumeTo
   | changeTempoBy
   | setTempoTo
   /**-------------PEN----------------*/
   | clearPenTrails
   | stampCostume
   | putPenDown
   | putPenUp
   | penColor
   | changePenHueBy
   | setPenHueTo
   | changePenShadeBy
   | setPenShadeTo
   | changePenSizeBy
   | penSize
   /**-------------DATA----------------*/
   | setVariableTo
   | changeVariableBy
   | showVariable
   | hideVariable
   | appendToList
   | deleteLineOfList
   | insertAtOfList
   | setLineOfListTo
   | showList
   | hideList

   /**-------------EVENTS----------------*/

   | broadcast
   | doBroadcastAndWait

   /**-------------CONTROL----------------*/

   | waitElapsedFrom
   | doWaitUntil
   | createCloneOf


   /**-------------SENSING----------------*/
   | doAsk
   | setVideoState
   | setVideoTransparency
   | timerReset
   ;

   forward
      : '[' FORWARD_TOKEN ',' singleOperand ']';

   turnRight
      : '[' TURN_RIGHT_TOKEN ',' singleOperand ']';

   turnLeft
      : '[' TURN_LEFT_TOKEN ',' singleOperand ']';

   heading
      : '[' HEADING_TOKEN ',' singleOperand ']';

   pointTowards
      : '[' POINT_TOWARDS_TOKEN ',' singleOperand ']';

   goToXY
      : '[' GOTO_XY_TOKEN ',' leftOperand',' rightOperand ']';

   goToSpriteOrMouse
      : '[' GOTO_SPRITE_OR_MOUSE_TOKEN ',' singleOperand ']';

   glideSecToXYElapsedFrom
      : '[' GLIDE_SEC_TO_X_Y_ELAPSED_FROM_TOKEN ',' leftOperand ',' centerOperand ',' rightOperand ']';

   changeXPosBy
      : '[' CHANGE_X_POS_BY_TOKEN ',' singleOperand ']';

   xPos
      : '[' X_POS_TOKEN ',' singleOperand ']';

   changeYposBy
      : '[' CHANGE_Y_POS_BY_TOKEN ',' singleOperand ']';

   yPos
      : '[' Y_POS_TOKEN ',' singleOperand ']';

   bounceOffEdge
      : '[' BOUNCE_OFF_EDGE_TOKEN ']';

   setRotationStyle
      : '[' SET_ROTATION_STYLE_TOKEN ',' singleOperand ']';

   sayDurationElapsedFrom
      : '[' SAY_DURATION_ELAPSEAD_FROM_TOKEN ',' leftOperand ',' rightOperand ']';

   sayToken
      : '[' SAY_TOKEN ',' singleOperand ']';

   thinkDurationElapsedFrom
      : '[' THINK_DURATION_ELAPSEAD_FROM_TOKEN ',' leftOperand ',' rightOperand ']';

   think
      : '[' THINK_TOKEN ',' singleOperand ']';

   show
      : '[' SHOW_TOKEN ']';

   hide
      : '[' HIDE_TOKEN ']';

   lookLike
      : '[' LOOK_LIKE_TOKEN ',' singleOperand ']';

   nextLook
      : '[' NEXT_LOOK_TOKEN ']';

   startScene
      : '[' START_SCENE_TOKEN ',' singleOperand ']';

   changeGraphicEffectBy
      : '[' CHANGE_GRAPHIC_EFFECT_BY_TOKEN ',' leftOperand ',' rightOperand ']';

   setGraphicEffect
      : '[' SET_GRAPHIC_EFFECT_BY_TOKEN ',' leftOperand ',' rightOperand ']';

   filterReset
      : '[' FILTER_RESET_TOKEN ']';

   changeSizeBy
      : '[' CHANGE_SIZE_BY_TOKEN ',' singleOperand ']';

   setSizeTo
      : '[' SET_SIZE_TO_TOKEN ',' singleOperand ']';

   comeToFront
      : '[' COME_TO_FRONT_TOKEN ']';

   goBackByLayers
      : '[' GO_BACK_BY_LAYERS_TOKEN ',' singleOperand ']';

   playSound
      : '[' PLAY_SOUND_TOKEN ',' singleOperand ']';

   doPlaySoundAndWait
      : '[' DO_PLAY_SOUND_AND_WAIT_TOKEN ',' singleOperand ']';

   stopAllSounds
      : '[' STOP_ALL_SOUNDS_TOKEN ']';

   playDrum
      : '[' PLAY_DRUM_TOKEN ',' leftOperand ',' rightOperand ']';

   restElapsedFrom
      : '[' REST_ELAPSED_FROM_TOKEN ',' singleOperand']';

   noteOnDurationElapsedFrom
      : '[' NOTE_ON_DURATION_ELAPSED_FROM_TOKEN ',' leftOperand ',' rightOperand ']';

   instrumentToken
      : '[' INSTRUMENT_TOKEN ',' singleOperand']';

   changeVolumeBy
      : '[' CHANGE_VOLUME_BY_TOKEN ',' singleOperand']';

   setVolumeTo
      : '[' SET_VOLUME_TO_TOKEN ',' singleOperand']';

   changeTempoBy
      : '[' CHANGE_TEMPO_TO_TOKEN ',' singleOperand']';

   setTempoTo
      : '[' SET_TEMPO_TO_TOKEN ',' singleOperand']';

   clearPenTrails
      : '[' CLEAR_PEN_TRAILS_TOKEN ']';

   stampCostume
      : '[' STAMP_COSTUME_TOKEN ']';

   putPenDown
      : '[' PUT_PEN_DOWN_TOKEN ']';

   putPenUp
      : '[' PUT_PEN_UP_TOKEN ']';

   penColor
      : '[' PEN_COLOR_TOKEN ',' singleOperand']';

   changePenHueBy
      : '[' CHANGE_PEN_HUE_BY_TOKEN ',' singleOperand']';

   setPenHueTo
      : '[' SET_PEN_HUE_TO_TOKEN ',' singleOperand']';

   changePenShadeBy
      : '[' CHANGE_PEN_SHADE_BY_TOKEN ',' singleOperand']';

   setPenShadeTo
      : '[' SET_PEN_SHADE_TO_TOKEN ',' singleOperand']';

   changePenSizeBy
      : '[' CHANGE_PEN_SIZE_BY_TOKEN ',' singleOperand']';

   penSize
      : '[' PEN_SIZE_TOKEN ',' singleOperand']';

   setVariableTo
      : '[' SET_VARIABLE_TO_TOKEN ',' leftOperand ',' rightOperand ']';

   changeVariableBy
      : '[' CHANGE_VARIABLE_BY_TOKEN ',' leftOperand ',' rightOperand ']';

   showVariable
      : '[' SHOW_VARIABLE_TOKEN ',' singleOperand']';

   hideVariable
      : '[' HIDE_VARIABLE_TOKEN ',' singleOperand']';

   readVariable
      : '[' READ_VARIABLE_TOKEN ',' singleOperand']';

   appendToList
      : '[' APPEND_TO_LIST_TOKEN ',' leftOperand ',' rightOperand ']';

   deleteLineOfList
      : '[' DELETE_LINE_OF_LIST_TOKEN ',' leftOperand ',' rightOperand ']';

   insertAtOfList
      : '[' INSERT_AT_OF_LIST_TOKEN ',' leftOperand ',' centerOperand ',' rightOperand ']';

   setLineOfListTo
      : '[' SET_LINE_OF_LIST_TO_TOKEN ',' leftOperand ',' centerOperand ',' rightOperand ']';

   showList
      : '[' SHOW_LIST_TOKEN  ',' singleOperand ']';

   hideList
      : '[' HIDE_LIST_TOKEN  ',' singleOperand ']';

   broadcast
      : '[' BROADCAST_TOKEN  ',' singleOperand  ']';

   doBroadcastAndWait
      : '[' DO_BROADCAST_AND_WAIT_TOKEN  ',' singleOperand  ']';

   waitElapsedFrom
      : '[' WAIT_ELAPSED_FROM_TOKEN ',' singleOperand']';

   doWaitUntil
      : '[' DO_WAIT_UNTIL_TOKEN ',' singleOperand ']';

   createCloneOf
      : '[' CREATE_CLONE_OF_TOKEN ',' singleOperand ']';

   doAsk
      : '[' DO_ASK_TOKEN ',' singleOperand ']';

   setVideoState
      : '[' SET_VIDEO_STATE_TOKEN ',' singleOperand ']';

   setVideoTransparency
      : '[' SET_VIDEO_TRANSPARENCY_TOKEN ',' singleOperand  ']';

   timerReset
      : '[' TIMER_RESET_TOKEN ']';

/**------------------------------------ CONTROL BLOCKS ------------------------------------------*/
controlBlock
   : doRepeat
   | doForever
   | doIf
   | doIfElse
   | doUntil
   ;

   doRepeat
      : '[' DO_REPEAT_TOKEN ',' NUMBER ',' statementArray ']'
      | '[' DO_REPEAT_TOKEN ',' statement ',' statementArray ']'
      | '[' DO_REPEAT_TOKEN ',' NUMBER ',' 'null' ']'
      | '[' DO_REPEAT_TOKEN ',' statement ',' 'null' ']';

   doForever
      : '[' DO_FOREVER_TOKEN ',' statementArray ']'
      | '[' DO_FOREVER_TOKEN ',' 'null' ']';

   doIf
      : '[' DO_IF_TOKEN ',' statement ',' thenBody ']'
      | '[' DO_IF_TOKEN ',' statement ',' 'null' ']'
      | '[' DO_IF_TOKEN ',' literalBoolean ',' thenBody ']'
      | '[' DO_IF_TOKEN ',' literalBoolean ',' 'null' ']';

   doIfElse
      : '[' DO_IF_ELSE_TOKEN ',' statement ',' thenBody ',' elseBody ']'
      | '[' DO_IF_ELSE_TOKEN ',' statement ',' 'null' ',' elseBody ']'
      | '[' DO_IF_ELSE_TOKEN ',' statement ',' thenBody ',' 'null' ']'
      | '[' DO_IF_ELSE_TOKEN ',' statement ',' 'null' ',' 'null' ']'
      | '[' DO_IF_ELSE_TOKEN ',' literalBoolean ',' thenBody ',' elseBody ']'
      | '[' DO_IF_ELSE_TOKEN ',' literalBoolean ',' 'null' ',' elseBody ']'
      | '[' DO_IF_ELSE_TOKEN ',' literalBoolean ',' thenBody ',' 'null' ']'
      | '[' DO_IF_ELSE_TOKEN ',' literalBoolean ',' 'null' ',' 'null' ']';

   doUntil
      : '[' DO_UNTIL_TOKEN ',' statement',' statementArray ']'
      | '[' DO_UNTIL_TOKEN ',' statement',' 'null' ']'
      | '[' DO_UNTIL_TOKEN ',' literalBoolean ',' statementArray ']'
      | '[' DO_UNTIL_TOKEN ',' literalBoolean ',' 'null' ']';

    thenBody
    : statementArray
    ;

    elseBody
    : statementArray
    ;


/**------------------------------------ CAP BLOCKS ------------------------------------------*/
capBlock
   /**-------------CONTROL----------------*/
   : stopScripts
   | deleteClone
   ;

   stopScripts
      : '[' STOP_SCRIPTS_TOKEN ',' literalString ']';

   deleteClone
      : '[' DELETE_CLONE_TOKEN ']';
/**------------------------------------ BOOLEAN BLOCKS ------------------------------------------*/
booleanBlock
   /**-------------SENSING----------------*/
   : touching
   | touchingColor
   | colorSees
   | distanceTo
   | whenKeyPressedSensing
   | mousePressed

   /**-------------DATA----------------*/
   | listContains

   /**-------------OPERATORS----------------*/
   | lower
   | equal
   | higher
   | and
   | or
   | not
   ;

touching
   : '[' TOUCHING_TOKEN ',' singleOperand ']';

touchingColor
   : '[' TOUCHING_COLOR_TOKEN ',' singleOperand ']';

colorSees
   : '[' COLOR_SEES_TOKEN ',' leftOperand ',' rightOperand ']';

distanceTo
   : '[' DISTANCE_TO_TOKEN ',' singleOperand ']';

whenKeyPressedSensing
   : '[' WHEN_KEY_PRESSED_SENSING_TOKEN ',' singleOperand ']';

mousePressed
   : '[' MOUSE_PRESSED_TOKEN ']';

listContains
   : '[' LIST_CONTAINS_TOKEN  ',' leftOperand  ',' rightOperand']';

lower
   : '[' LOWER_TOKEN ',' leftOperand ',' rightOperand']';

equal
   : '[' EQUAL_TOKEN ',' leftOperand ',' rightOperand']';

higher
   : '[' HIGHER_TOKEN ',' leftOperand ',' rightOperand']';

and
   : '[' AND_TOKEN ',' leftOperand ',' rightOperand']';

or
   : '[' OR_TOKEN ',' leftOperand ',' rightOperand']';

not
   : '[' NOT_TOKEN ',' singleOperand']';

leftOperand
    : literalString
    | literalBoolean
    | NUMBER
    | statement
    ;

centerOperand
    : literalString
    | literalBoolean
    | NUMBER
    | statement
    ;
rightOperand
    : literalString
    | literalBoolean
    | NUMBER
    | statement
    ;
singleOperand
    : literalString
    | literalBoolean
    | NUMBER
    | statement
    ;

/**------------------------------------ REPORTER BLOCKS ------------------------------------------*/
reporterBlock
    /**-------------MOTION----------------*/
   : yPosSingle
   | xPosSingle
   | headingSingle

    /**-------------LOOK----------------*/
   | costumeIndex
   | sceneName
   | scaleReporter

    /**-------------SOUND----------------*/
   | volume
   | tempo

   /**-------------DATA----------------*/

   | readVariable
   | getLineOfList
   | lineCountOfList
   | contentsOfList

   /**-------------SENSING----------------*/
   | answer
   | mouseX
   | mouseY
   | soundLevel
   | senseVideoMotion
   | timer
   | getAttributeOf
   | timeAndDate
   | timestamp
   | getUserName

   /**-------------OPERATORS----------------*/
   | plus
   | minus
   | multiply
   | divider
   | randomFromTo
   | concatenateWith
   | letterOf
   | stringLength
   | mod
   | rounded
   | computeFunctionOf
   ;

yPosSingle
   : '[' Y_POS_SINGLE_TOKEN ']';

xPosSingle
   : '[' X_POS_SINGLE_TOKEN ']';

headingSingle
   : '[' HEADING_SINGLE_TOKEN ']';

costumeIndex
   : '[' COSTUME_INDEX_TOKEN ']';

sceneName
   : '[' SCENE_NAME_TOKEN ']';

scaleReporter
   : '[' SCALE_TOKEN ']';

volume
   : '[' VOLUME_TOKEN ']';

tempo
   : '[' TEMPO_TOKEN ']';

getLineOfList
   : '[' GET_LINE_OF_LIST_TOKEN ',' leftOperand ',' rightOperand ']';

lineCountOfList
   : '[' LINE_COUNT_OF_LIST_TOKEN ',' singleOperand ']';

contentsOfList
   : '[' CONTENTS_OF_LIST_TOKEN  ',' singleOperand ']';

answer
   : '[' ANSWER_TOKEN ']';

mouseX
   : '[' MOUSE_X_TOKEN ']';

mouseY
   : '[' MOUSE_Y_TOKEN ']';

soundLevel
   : '[' SOUND_LEVEL_TOKEN ']';

senseVideoMotion
   : '[' SENSE_VIDEO_MOTION_TOKEN ',' leftOperand ',' rightOperand ']';

timer
   : '[' TIMER_TOKEN ']';

getAttributeOf
   : '[' GET_ATTRIBUTE_OF_TOKEN ',' leftOperand ',' rightOperand ']';

timeAndDate
   : '[' TIME_AND_DATE_TOKEN ',' singleOperand']';

timestamp
   : '[' TIME_STAMP_TOKEN']';

getUserName
   : '[' GET_USERNAME_TOKEN']';

plus
   : '[' PLUS_TOKEN ',' leftOperand ',' rightOperand']';

minus
   : '[' MINUS_TOKEN ',' leftOperand ',' rightOperand']';

multiply
   : '[' MULTIPLY_TOKEN ',' leftOperand ',' rightOperand']';

divider
   : '[' DIVIDER_TOKEN ',' leftOperand ',' rightOperand']';

randomFromTo
   : '[' RANDOM_FROM_TO_TOKEN ',' leftOperand ',' rightOperand']';

concatenateWith
   : '[' CONCATENATE_WITH_TOKEN ',' leftOperand ',' rightOperand']';

letterOf
   : '[' LETTER_OF_TOKEN ',' leftOperand ',' rightOperand']';

stringLength
   : '[' STRING_LENGTH_TOKEN ',' singleOperand']';

mod
   : '[' MOD_TOKEN ',' leftOperand ',' rightOperand']';

rounded
   : '[' ROUNDED_TOKEN ',' singleOperand']';

computeFunctionOf
   : '[' COMPUTE_FUNCTION_OF_TOKEN ',' leftOperand ',' rightOperand']';



/**------------------------------------ END SCRIPT BLOCKS ------------------------------------------*/
variables
   : VARIABLES_TOKEN ':' variableArray
   ;

variableArray
   : '[' variableBlock (',' variableBlock)* ']'
   ;

variableBlock
   : '{' (name | variableValue | isPersistent )* ((',' name)
      | (',' variableValue)
      | (',' isPersistent))*  '}'
   ;

name
   : NAME_TOKEN ':' literalString
   ;

variableValue
   : VALUE_TOKEN ':' literalString
   | VALUE_TOKEN ':' NUMBER
   ;

isPersistent
   : IS_PERSISTENT_TOKEN ':' 'true'
   | IS_PERSISTENT_TOKEN ':' 'false'
   ;

list
   : LIST_TOKEN ':' listArray
   ;

listArray
   : '[' listBlock (',' listBlock)* ']'
   ;

listBlock
   : '{' (listName | contents | isPersistent | x | y | width | height | visible )* ((',' listName)
      | (',' contents)
      | (',' isPersistent)
      | (',' x)
      | (',' y)
      | (',' width)
      | (',' height)
      | (',' visible))*  '}'
   ;

listName
   : LIST_NAME_TOKEN ':' literalString
   ;

contents
   : CONTENTS_TOKEN ':' '[' literalString (',' literalString)* ']'
   | CONTENTS_TOKEN ':' '[' ']'
   ;

x
   : X_TOKEN ':' NUMBER
   ;

y
   : Y_TOKEN ':' NUMBER
   ;

width
   : WIDTH_TOKEN ':' NUMBER
   ;

height
   : HEIGHT_TOKEN ':' NUMBER
   ;

sounds
   :SOUNDS_TOKEN ':' soundsArray
   ;

/**------------------------------------ BEGIN SOUNDS BLOCKS ------------------------------------------*/
soundsArray
   :'[' sounds_block (',' sounds_block)* ']'
   ;

sounds_block:
   '{' (sound_name | soundID | md5 | sampleCount | rate | format)* ((',' sound_name)
      | (',' soundID)
      | (',' md5)
      | (',' sampleCount)
      | (',' rate)
      | (',' format))*  '}';


sound_name:
      SOUND_NAME_TOKEN ':' literalString;

   soundID:
      SOUND_ID_TOKEN ':' NUMBER;

   md5:
      MD5_TOKEN ':' literalString;

   sampleCount:
      SAMPLE_COUNT_TOKEN ':' NUMBER;

   rate:
      RATE_TOKEN ':' NUMBER;

   format:
      FORMAT_TOKEN ':' literalString;

/**------------------------------------ END SOUNDS BLOCKS ------------------------------------------*/
scale
   :SCALE_TOKEN ':' NUMBER;


costumes
   :COSTUMES_TOKEN ':' costumesArray
   ;

/**------------------------------------ BEGIN COSTUMES BLOCKS ------------------------------------------*/
costumesArray
   : '[' costume_block (',' costume_block)* ']'
;

costume_block
   : '{' ( costumeName | baseLayerID | baseLayerMD5 | bitmapResolution | rotationCenterX | rotationCenterY)* ((',' costumeName)
      | (',' baseLayerID)
      | (',' baseLayerMD5)
      | (',' bitmapResolution)
      | (',' rotationCenterX)
      | (',' rotationCenterY))*  '}';

costumeName:
      COSTUME_NAME_TOKEN ':' literalString;

baseLayerID:
      BASE_LAYER_ID_TOKEN ':' NUMBER;

baseLayerMD5:
      BASE_LAYER_MD5_TOKEN ':' literalString;

bitmapResolution:
      BIT_MAP_RESOLUTION_TOKEN ':' NUMBER;

rotationCenterX:
      ROTATION_CENTER_X_TOKEN ':' NUMBER;

rotationCenterY:
      ROTATION_CENTER_Y_TOKEN ':' NUMBER;


/**------------------------------------ END COSTUMES BLOCKS ------------------------------------------*/

currentCostumeIndex:
   CURRENT_COSTUME_INDEX_TOKEN ':' NUMBER
   ;

penLayerMD5
   :PEN_LAYER_MD5_TOKEN ':' literalString
   | PEN_LAYER_MD5_TOKEN ':' 'null'
   ;

penLayerID
   :PEN_LAYER_ID_TOKEN ':' NUMBER
   ;

tempoBPM
   :TEMPO_BPM_TOKEN ':' NUMBER
   ;

videoAlpha
   :VIDEO_APLHA_TOKEN ':' NUMBER
   ;
children
   : CHILDREN_TOKEN ':' children_blocks
   ;

children_blocks
   : '[' spriteObject ((',' spriteObject) | (',' variableDisplay) | (',' listBlock) )* ']'
   | '[' variableDisplay ((',' spriteObject) | (',' variableDisplay) | (',' listBlock) )* ']'
   | '[' listBlock ((',' spriteObject) | (',' variableDisplay) | (',' listBlock) )* ']'
   ;

scratchX
   : SCRATCH_X_TOKEN ':' NUMBER
   ;

scratchY
   : SCRATCH_Y_TOKEN ':' NUMBER
   ;

direction
   : DIRECTION_TOKEN ':' NUMBER
   ;

rotationStyle
   : ROTATION_STYLE_TOKEN ':' literalString
   ;

isDraggable
   : IS_DRAGGABLE_TOKEN ':' 'true'
   | IS_DRAGGABLE_TOKEN ':' 'false'
   ;

indexInLibrary
   : INDEX_IN_LIBRARY_TOKEN ':' NUMBER
   ;

visible
   : VISIBLE_TOKEN ':' 'true'
   | VISIBLE_TOKEN ':' 'false'
   ;

spriteInfo
   :SPRITE_INFO_TOKEN ':' value
   ;

info
   : INFO_TOKEN ':' infoArray
   ;

variableDisplay
   : '{' (target | cmd | param | color | label | modeDisplay | sliderMin | sliderMax
      | isDiscrete | x | y | visible)* ((',' target)
      | (',' cmd)
      | (',' param)
      | (',' color)
      | (',' label)
      | (',' modeDisplay)
      | (',' sliderMin)
      | (',' sliderMax)
      | (',' isDiscrete)
      | (',' x)
      | (',' y)
      | (',' visible))*  '}'
   | '{' '}'
   ;

target
   : TARGET_TOKEN ':' literalString
   ;

cmd
   : CMD_TOKEN ':' literalString
   ;

param
   : PARAM_TOKEN ':' literalString
   ;

color
   : COLOR_TOKEN ':' NUMBER
   ;


label
   : LABEL_TOKEN ':' literalString
   ;

modeDisplay
   : MODE_TOKEN ':' NUMBER
   ;

sliderMin
   : SLIDER_MIN_TOKEN ':' NUMBER
   ;

sliderMax
   : SLIDER_MAX_TOKEN ':' NUMBER
   ;

isDiscrete
   : IS_DISCRETE_TOKEN ':' 'true'
   | IS_DISCRETE_TOKEN ':' 'false'
   ;

/**------------------------------------ BEGIN INFO BLOCKS ------------------------------------------*/
infoArray
   : '{' ( flashVersion | projectID | hasCloudData | spriteCount | videoOn | swfVersion | userAgent | scriptCount )* ((',' flashVersion)
      | (',' spriteCount)
      | (',' projectID)
      | (',' hasCloudData)
      | (',' videoOn)
      | (',' swfVersion)
      | (',' userAgent)
      | (',' scriptCount))*  '}';

flashVersion
   : FLASH_VERSION_TOKEN ':' literalString
   ;

projectID
   : PROJECT_ID_TOKEN ':' literalString
   ;

hasCloudData
   : HAS_CLOUD_DATA_TOKEN ':' 'true'
   | HAS_CLOUD_DATA_TOKEN ':' 'false'
   ;

spriteCount
   : SPRITE_COUNT_TOKEN ':' NUMBER
   ;

videoOn
   : VIDEO_ON_TOKEN ':' 'true'
   | VIDEO_ON_TOKEN ':' 'false'
   ;

swfVersion
   : SWF_VERSION_TOKEN ':' literalString
   ;

userAgent
   : USER_AGENT_TOKEN ':' literalString
   ;

scriptCount
   : SCRIPT_COUNT_TOKEN ':' NUMBER
   ;

/**------------------------------------ END INFO BLOCKS ------------------------------------------*/

/**------------------------------------ END SPRITE OBJECT ------------------------------------------*/


/** ---------------------- KEYWORDS --------------------------- */

/** ---------------------- FLASH_VERSION PROPERTIES --------------------------- */
INFO_TOKEN
   : '"' 'info' '"';
/** ---------------------- FLASH_VERSION PROPERTIES --------------------------- */
FLASH_VERSION_TOKEN
   : '"' 'flashVersion' '"';
PROJECT_ID_TOKEN
   : '"' 'projectID' '"'
   ;

HAS_CLOUD_DATA_TOKEN
   : '"' 'hasCloudData' '"'
   ;


/** ---------------------- SPRITE_COUNT PROPERTIES --------------------------- */
SPRITE_COUNT_TOKEN
   : '"' 'spriteCount' '"';

/** ---------------------- VIDEO_ON PROPERTIES --------------------------- */
VIDEO_ON_TOKEN
   : '"' 'videoOn' '"';

/** ---------------------- SWF_VERSION PROPERTIES --------------------------- */
SWF_VERSION_TOKEN
   : '"' 'swfVersion' '"';

/** ---------------------- USER_AGENT PROPERTIES --------------------------- */
USER_AGENT_TOKEN
   : '"' 'userAgent' '"';

/** ---------------------- SCRIPT_COUNT PROPERTIES --------------------------- */
SCRIPT_COUNT_TOKEN
   : '"' 'scriptCount' '"';

/** ---------------------- SCRATCH_X PROPERTIES --------------------------- */
SCRATCH_X_TOKEN
   : '"' 'scratchX' '"';

/** ---------------------- SCRATCH_Y PROPERTIES --------------------------- */
SCRATCH_Y_TOKEN
   : '"' 'scratchY' '"';

/** ---------------------- DIRECTION PROPERTIES --------------------------- */
DIRECTION_TOKEN
   : '"' 'direction' '"';

/** ---------------------- ROTATION_STYLE PROPERTIES --------------------------- */
ROTATION_STYLE_TOKEN
   : '"' 'rotationStyle' '"';

/** ---------------------- IS_DRAGGABLE PROPERTIES --------------------------- */
IS_DRAGGABLE_TOKEN
   : '"' 'isDraggable' '"';

/** ---------------------- INDEX_IN_LIBRARY PROPERTIES --------------------------- */
INDEX_IN_LIBRARY_TOKEN
   : '"' 'indexInLibrary' '"';

/** ---------------------- VISIBLE PROPERTIES --------------------------- */
VISIBLE_TOKEN
   : '"' 'visible' '"';

/** ---------------------- SPRITE_INFO_ PROPERTIES --------------------------- */
SPRITE_INFO_TOKEN
   : '"' 'spriteInfo' '"';

/** ---------------------- CHILDREN PROPERTIES --------------------------- */
CHILDREN_TOKEN
   : '"' 'children' '"';

/** ---------------------- PEN_LAYER_MD5_TOKEN PROPERTIES --------------------------- */
PEN_LAYER_MD5_TOKEN
   : '"' 'penLayerMD5' '"';

/** ---------------------- PEN_LAYER_ID_TOKEN PROPERTIES --------------------------- */
PEN_LAYER_ID_TOKEN
   : '"' 'penLayerID' '"';

/** ---------------------- TEMPO_BPM_TOKEN PROPERTIES --------------------------- */
TEMPO_BPM_TOKEN
   : '"' 'tempoBPM' '"';

/** ---------------------- VIDEO_APLHA_TOKEN PROPERTIES --------------------------- */
VIDEO_APLHA_TOKEN
   : '"' 'videoAlpha' '"';
/** ---------------------- CURRENT_COSTUME_INDEX PROPERTIES --------------------------- */
CURRENT_COSTUME_INDEX_TOKEN
   : '"' 'currentCostumeIndex' '"';

/** ---------------------- SCRIPTS PROPERTIES --------------------------- */

SCRIPTS_TOKEN
   : '"' 'scripts' '"';
/** ---------------------- OBJ_NAME PROPERTIES --------------------------- */
OBJ_NAME_TOKEN
   : '"' 'objName' '"';

/** ---------------------- VARIABLES PROPERTIES --------------------------- */
VARIABLES_TOKEN
   : '"' 'variables' '"'
   ;
NAME_TOKEN
   : '"' 'name' '"'
   ;
VALUE_TOKEN
   : '"' 'value' '"'
   ;
IS_PERSISTENT_TOKEN
   : '"' 'isPersistent' '"'
   ;
/** ---------------------- LIST PROPERTIES --------------------------- */
LIST_TOKEN
   : '"' 'lists' '"'
   ;

LIST_NAME_TOKEN
   : '"' 'listName' '"'
   ;

CONTENTS_TOKEN
   : '"' 'contents' '"'
   ;

X_TOKEN
   : '"' 'x' '"'
   ;

Y_TOKEN
   : '"' 'y' '"'
   ;

WIDTH_TOKEN
   : '"' 'width' '"'
   ;

HEIGHT_TOKEN
   : '"' 'height' '"'
   ;

/** ---------------------- VARIABLES DISPLAY PROPERTIES --------------------------- */

TARGET_TOKEN
   : '"' 'target' '"'
   ;
CMD_TOKEN
   : '"' 'cmd' '"'
   ;
PARAM_TOKEN
   : '"' 'param' '"'
   ;

COLOR_TOKEN
   : '"' 'color' '"'
   ;

LABEL_TOKEN
   : '"' 'label' '"'
   ;

MODE_TOKEN
   : '"' 'mode' '"'
   ;

SLIDER_MIN_TOKEN
   : '"' 'sliderMin' '"'
   ;

SLIDER_MAX_TOKEN
   : '"' 'sliderMax' '"'
   ;

IS_DISCRETE_TOKEN
   : '"' 'isDiscrete' '"'
   ;
/** ---------------------- SOUNDS PROPERTIES --------------------------- */

   SOUNDS_TOKEN
      : '"' 'sounds' '"';


   SOUND_NAME_TOKEN
      : '"' 'soundName' '"';

   SOUND_ID_TOKEN
      : '"' 'soundID' '"';

   MD5_TOKEN
      : '"' 'md5' '"';

   SAMPLE_COUNT_TOKEN
      : '"' 'sampleCount' '"';

   RATE_TOKEN
      : '"' 'rate' '"';

   FORMAT_TOKEN
      : '"' 'format' '"';

/** ---------------------- COSTUMES PROPERTIES --------------------------- */
COSTUMES_TOKEN
   : '"' 'costumes' '"';


COSTUME_NAME_TOKEN
   : '"' 'costumeName' '"';

BASE_LAYER_ID_TOKEN
   : '"' 'baseLayerID' '"';

BASE_LAYER_MD5_TOKEN
   : '"' 'baseLayerMD5' '"';

BIT_MAP_RESOLUTION_TOKEN
   : '"' 'bitmapResolution' '"';

ROTATION_CENTER_X_TOKEN
   : '"' 'rotationCenterX' '"';

ROTATION_CENTER_Y_TOKEN
   : '"' 'rotationCenterY' '"';

/** ---------------------- Motion Tokens --------------------------- */
/** -- Move Token -- */
FORWARD_TOKEN
   : '"' 'forward:' '"';

/** --  Turn Right Token -- */

TURN_RIGHT_TOKEN
   : '"' 'turnRight:' '"';

/** -- Turn Left Token -- */
TURN_LEFT_TOKEN
   : '"' 'turnLeft:' '"';

/** -- Point in direction Token -- */
HEADING_TOKEN
   : '"' 'heading:' '"';

/** -- Point Torwards Token -- */
POINT_TOWARDS_TOKEN
   : '"' 'pointTowards:' '"';

/** --  Go to X Y Token -- */
GOTO_XY_TOKEN
   : '"' 'gotoX:y:' '"';

/** -- Go to Token -- */
GOTO_SPRITE_OR_MOUSE_TOKEN
   : '"' 'gotoSpriteOrMouse:' '"';

/** -- Glide secs to X Y Token -- */

GLIDE_SEC_TO_X_Y_ELAPSED_FROM_TOKEN
   : '"' 'glideSecs:toX:y:elapsed:from:' '"';

/** -- Change X Token -- */
CHANGE_X_POS_BY_TOKEN
   : '"' 'changeXposBy:' '"';

/** -- Set X Token -- */

X_POS_TOKEN
   : '"' 'xpos:' '"';
/** -- Change Y by Token -- */

CHANGE_Y_POS_BY_TOKEN
   : '"' 'changeYposBy:' '"';

/** -- Set Y Token -- */

Y_POS_TOKEN
   : '"' 'ypos:' '"';
/** -- If on edge Token -- */

BOUNCE_OFF_EDGE_TOKEN
   : '"' 'bounceOffEdge' '"';

/** -- Set rotation style Token -- */
SET_ROTATION_STYLE_TOKEN
   : '"' 'setRotationStyle' '"';

/** -- X position Token -- */
X_POS_SINGLE_TOKEN
   : '"' 'xpos' '"';
/** -- Y position Token -- */
Y_POS_SINGLE_TOKEN
   : '"' 'ypos' '"';
/** -- Direction Token -- */
HEADING_SINGLE_TOKEN
   : '"' 'heading' '"';


/** ---------------------- Looks Tokens --------------------------- */
/** --  Say For Seconds Token -- */

SAY_DURATION_ELAPSEAD_FROM_TOKEN
   : '"' 'say:duration:elapsed:from:' '"';

/** --  Say Token -- */
SAY_TOKEN
   : '"' 'say:' '"';

/** --  Think For Seconds Token -- */
THINK_DURATION_ELAPSEAD_FROM_TOKEN
   : '"' 'think:duration:elapsed:from:' '"';

/** --  Think Token -- */
THINK_TOKEN
   : '"' 'think:' '"';

/** --  Show Token -- */
SHOW_TOKEN
   : '"' 'show' '"';

/** -- Hide Token -- */
HIDE_TOKEN
   : '"' 'hide' '"';

/** -- Switch Costume Token -- */
LOOK_LIKE_TOKEN
   : '"' 'lookLike:' '"';

/** --  Next Costume Token -- */
NEXT_LOOK_TOKEN
   : '"' 'nextCostume' '"';

/** --  Swith backdrop Token -- */
START_SCENE_TOKEN
   : '"' 'startScene' '"';

/** -- Change effect by Token -- */
CHANGE_GRAPHIC_EFFECT_BY_TOKEN
   : '"' 'changeGraphicEffect:by:' '"';

/** -- Set effect to Token -- */
SET_GRAPHIC_EFFECT_BY_TOKEN
   : '"' 'setGraphicEffect:to:' '"';

/** --  Clear graphic Token -- */
FILTER_RESET_TOKEN
   : '"' 'filterReset' '"';

/** -- Change size Token -- */
CHANGE_SIZE_BY_TOKEN
   : '"' 'changeSizeBy:' '"';

/** -- Set size Token -- */
SET_SIZE_TO_TOKEN
   : '"' 'setSizeTo:' '"';

/** -- Go to front Token -- */
COME_TO_FRONT_TOKEN
   : '"' 'comeToFront' '"';

/** -- Go back layers Token -- */
GO_BACK_BY_LAYERS_TOKEN
   : '"' 'goBackByLayers:' '"';

/** -- Costume Token -- */
COSTUME_INDEX_TOKEN
   : '"' 'costumeIndex' '"';

/** -- Backdrop name Token -- */
SCENE_NAME_TOKEN
   : '"' 'sceneName' '"';

/** --Size Token -- */
SCALE_TOKEN
   : '"' 'scale' '"';

/** ---------------------- Sound Tokens --------------------------- */
/** -- Play sound until Token -- */
PLAY_SOUND_TOKEN
   : '"' 'playSound:' '"';

/** -- Start sound Token -- */
DO_PLAY_SOUND_AND_WAIT_TOKEN
   : '"' 'doPlaySoundAndWait' '"';

/** -- Stop all sounds Token -- */
STOP_ALL_SOUNDS_TOKEN
   : '"' 'stopAllSounds' '"';

/** -- Play drum for Token -- */
PLAY_DRUM_TOKEN
   : '"' 'playDrum' '"';

/** -- Rest for Token -- */
REST_ELAPSED_FROM_TOKEN
   : '"' 'rest:elapsed:from:' '"';

/** -- Play note Token -- */
NOTE_ON_DURATION_ELAPSED_FROM_TOKEN
   : '"' 'noteOn:duration:elapsed:from:' '"';

/** -- Set instrument Token -- */
INSTRUMENT_TOKEN
   : '"' 'instrument:' '"';

/** -- Change volume by Token -- */
CHANGE_VOLUME_BY_TOKEN
   : '"' 'changeVolumeBy:' '"';

/** -- Set volumen to Token -- */
SET_VOLUME_TO_TOKEN
   : '"' 'setVolumeTo:' '"';

/** -- Volumne Token -- */
VOLUME_TOKEN
   : '"' 'volume' '"';

/** -- Change tempo by Token -- */
CHANGE_TEMPO_TO_TOKEN
   : '"' 'changeTempoBy:' '"';

/** -- Set tempo to Token -- */
SET_TEMPO_TO_TOKEN
   : '"' 'setTempoTo:' '"';

/** -- Tempo Token -- */
TEMPO_TOKEN
   : '"' 'tempo' '"';

/** ---------------------- Pen Tokens --------------------------- */
/** -- Clear Token -- */
CLEAR_PEN_TRAILS_TOKEN
   : '"' 'clearPenTrails' '"';

/** -- Stamp Token -- */
STAMP_COSTUME_TOKEN
   : '"' 'stampCostume' '"';

/** -- Pen down Token -- */
PUT_PEN_DOWN_TOKEN
   : '"' 'putPenDown' '"';

/** -- Pen up Token -- */
PUT_PEN_UP_TOKEN
   : '"' 'putPenUp' '"';

/** -- Set pen color to Token -- */
PEN_COLOR_TOKEN
   : '"' 'penColor:' '"';

/** -- Change pen color by Token -- */
CHANGE_PEN_HUE_BY_TOKEN
   : '"' 'changePenHueBy:' '"';

/** -- Set pen color to number Token -- */
SET_PEN_HUE_TO_TOKEN
   : '"' 'setPenHueTo:' '"';

/** -- Change pen shade by Token -- */
CHANGE_PEN_SHADE_BY_TOKEN
   : '"' 'changePenShadeBy:' '"';

/** -- Set pen shade to Token -- */
SET_PEN_SHADE_TO_TOKEN
   : '"' 'setPenShadeTo:' '"';

/** -- Change pen size Token -- */
CHANGE_PEN_SIZE_BY_TOKEN
   : '"' 'changePenSizeBy:' '"';

/** -- Set pen size to Token -- */
PEN_SIZE_TOKEN
   : '"' 'penSize:' '"';

/** ---------------------- Data Tokens --------------------------- */
/** --  Set Variable To Token -- */
SET_VARIABLE_TO_TOKEN
   : '"' 'setVar:to:' '"';

/** --  Change Variable To Token -- */
CHANGE_VARIABLE_BY_TOKEN
   : '"' 'changeVar:by:' '"';

/** --  Show Variable To Token -- */
SHOW_VARIABLE_TOKEN
   : '"' 'showVariable:' '"';

/** --  Hide Variable To Token -- */
HIDE_VARIABLE_TOKEN
   : '"' 'hideVariable:' '"';

/** --  Read Variable To Token -- */
READ_VARIABLE_TOKEN
   : '"' 'readVariable' '"';

/** --  Add to Token -- */
APPEND_TO_LIST_TOKEN
   : '"' 'append:toList:' '"';

/** --  Delete of Token -- */
DELETE_LINE_OF_LIST_TOKEN
   : '"' 'deleteLine:ofList:' '"';

/** --  Insert at of Token -- */
INSERT_AT_OF_LIST_TOKEN
   : '"' 'insert:at:ofList:' '"';

/** --  Replace item at of with Token -- */
SET_LINE_OF_LIST_TO_TOKEN
   : '"' 'setLine:ofList:to:' '"';

/** --  Item of Token -- */
GET_LINE_OF_LIST_TOKEN
   : '"' 'getLine:ofList:' '"';

/** --  Length of Token -- */
LINE_COUNT_OF_LIST_TOKEN
   : '"' 'lineCountOfList:' '"';

/** --  Contains Token -- */
LIST_CONTAINS_TOKEN
   : '"' 'list:contains:' '"';

/** --  Show list Token -- */
SHOW_LIST_TOKEN
   : '"' 'showList:' '"';

/** --  Hide list Token -- */
HIDE_LIST_TOKEN
   : '"' 'hideList:' '"';

/** -- Content of list Token -- */
CONTENTS_OF_LIST_TOKEN
   : '"' 'contentsOfList:' '"';


/** ---------------------- Event Tokens --------------------------- */
/** --  When Green Flag Token -- */
WHEN_GREEN_FLAG_TOKEN
   : '"' 'whenGreenFlag' '"';

/** --  When key pressed Token -- */
WHEN_KEY_PRESSED_TOKEN
   : '"' 'whenKeyPressed' '"';

/** --  When sprite clicked Token -- */
WHEN_CLICKED_TOKEN
   : '"' 'whenClicked' '"';

/** --  When backdrop switches Token -- */
WHEN_SCENE_STARTS_TOKEN
   : '"' 'whenSceneStarts' '"';

/** --  When greater Token -- */
WHEN_SENSOR_GREATER_THAN_TOKEN
   : '"' 'whenSensorGreaterThan' '"';

/** --  When I receive Token -- */
WHEN_I_RECEIVE_TOKEN
   : '"' 'whenIReceive' '"';

/** --  Broadcast Token -- */
BROADCAST_TOKEN
   : '"' 'broadcast:' '"';

/** --  Broadcast and wait Token -- */
DO_BROADCAST_AND_WAIT_TOKEN
   : '"' 'doBroadcastAndWait' '"';

/** ---------------------- Control Tokens --------------------------- */
/** --  Wait seconds Token -- */
WAIT_ELAPSED_FROM_TOKEN
   : '"' 'wait:elapsed:from:' '"';

/** --  Repeat Token -- */
DO_REPEAT_TOKEN
   : '"' 'doRepeat' '"';

/** --  Forever Token -- */
DO_FOREVER_TOKEN
   : '"' 'doForever' '"';

/** --  If Token -- */
DO_IF_TOKEN
   : '"' 'doIf' '"';

/** --  IfElse Token -- */
DO_IF_ELSE_TOKEN
   : '"' 'doIfElse' '"';

/** --  Wait until Token -- */
DO_WAIT_UNTIL_TOKEN
   : '"' 'doWaitUntil' '"';

/** --  Repeat until Token -- */
DO_UNTIL_TOKEN
   : '"' 'doUntil' '"';

/** --  Stop Token -- */
STOP_SCRIPTS_TOKEN
   : '"' 'stopScripts' '"';

/** --  When I start as a clone Token -- */
WHEN_CLONED_TOKEN
   : '"' 'whenCloned' '"';

/** --  Create clone Token -- */
CREATE_CLONE_OF_TOKEN
   : '"' 'createCloneOf' '"';

/** --  Delete clone Token -- */
DELETE_CLONE_TOKEN
   : '"' 'deleteClone' '"';

/** ---------------------- Sensing Tokens --------------------------- */
/** --  Touching Token -- */
TOUCHING_TOKEN
   : '"' 'touching:' '"';

/** --  Touching color Token -- */
TOUCHING_COLOR_TOKEN
   : '"' 'touchingColor:' '"';

/** --  Color is touching Token -- */
COLOR_SEES_TOKEN
   : '"' 'color:sees:' '"';

/** --  Distance to Token -- */
DISTANCE_TO_TOKEN
   : '"' 'distanceTo:' '"';

/** --  Ask and Wait Token -- */
DO_ASK_TOKEN
   : '"' 'doAsk' '"';

/** --  Answer Token -- */
ANSWER_TOKEN
   : '"' 'answer' '"';

/** --  Key space pressed? Token -- */
WHEN_KEY_PRESSED_SENSING_TOKEN
   : '"' 'keyPressed:' '"';

/** --  Mouse down Token -- */
MOUSE_PRESSED_TOKEN
   : '"' 'mousePressed' '"';

/** --  Mouse X Token -- */
MOUSE_X_TOKEN
   : '"' 'mouseX' '"';

/** --  Mouse Y Token -- */
MOUSE_Y_TOKEN
   : '"' 'mouseY' '"';

/** --  Loudness Token -- */
SOUND_LEVEL_TOKEN
   : '"' 'soundLevel' '"';

/** --  Video on Token -- */
SENSE_VIDEO_MOTION_TOKEN
   : '"' 'senseVideoMotion' '"';

/** --  Turn video Token -- */
SET_VIDEO_STATE_TOKEN
   : '"' 'setVideoState' '"';

/** --  Set video transparency Token -- */
SET_VIDEO_TRANSPARENCY_TOKEN
   : '"' 'setVideoTransparency' '"';

/** --  Timer Token -- */
TIMER_TOKEN
   : '"' 'timer' '"';

/** --  Reset timer Token -- */
TIMER_RESET_TOKEN
   : '"' 'timerReset' '"';

/** --  Of Token -- */
GET_ATTRIBUTE_OF_TOKEN
   : '"' 'getAttribute:of:' '"';

/** --  Current Token -- */
TIME_AND_DATE_TOKEN
   : '"' 'timeAndDate' '"';

/** --  Days since Token -- */
TIME_STAMP_TOKEN
   : '"' 'timestamp' '"';

/** --  Username Token -- */
GET_USERNAME_TOKEN
   : '"' 'getUserName' '"';

/** ---------------------- Operator Tokens --------------------------- */
PLUS_TOKEN
   : '"' '+' '"';

MINUS_TOKEN
   : '"' '-' '"';

MULTIPLY_TOKEN
   : '"' '*' '"';

DIVIDER_TOKEN
   : '"' '\\/' '"';

RANDOM_FROM_TO_TOKEN
   : '"' 'randomFrom:to:' '"';

HIGHER_TOKEN
   : '"' '>' '"';

LOWER_TOKEN
   : '"' '<' '"';

EQUAL_TOKEN
   : '"' '=' '"';

AND_TOKEN
   : '"' '&' '"';

OR_TOKEN
   : '"' '|' '"';

NOT_TOKEN
   : '"' 'not' '"';

CONCATENATE_WITH_TOKEN
   : '"' 'concatenate:with:' '"';

LETTER_OF_TOKEN
   : '"' 'letter:of:' '"';

STRING_LENGTH_TOKEN
   : '"' 'stringLength:' '"';

MOD_TOKEN
   : '"' '%' '"';

ROUNDED_TOKEN
   : '"' 'rounded' '"';

COMPUTE_FUNCTION_OF_TOKEN
   : '"' 'computeFunction:of:' '"';


/** ---------------------- My blocks Tokens --------------------------- */

// This rule have all tokens, to prevent when a literalString is equal a terminal node.
//TODO
literalString
    : STRING
    | COLOR_TOKEN
    ;

literalBoolean
    : 'true'
    | 'false'
    ;

value
   : STRING
   | NUMBER
   | spriteObject
   | statement
   ;


STRING
   : '"' (ESC | SAFECODEPOINT)* '"'
   ;


fragment ESC
   : '\\' (["\\/bfnrt] | UNICODE)
   ;
fragment UNICODE
   : 'u' HEX HEX HEX HEX
   ;
fragment HEX
   : [0-9a-fA-F]
   ;
fragment SAFECODEPOINT
   : ~ ["\\\u0000-\u001F]
   ;


NUMBER
   : '-'? INT ('.' [0-9] +)? EXP?
   ;


fragment INT
   : '0' | [1-9] [0-9]*
   ;

// no leading zeros

fragment EXP
   : [Ee] [+\-]? INT
   ;

// \- since - means "range" inside [...]

WS
   : [ \t\n\r] + -> skip
   ;