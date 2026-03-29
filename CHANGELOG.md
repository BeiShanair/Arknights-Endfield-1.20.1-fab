# 更新日志 / Changelog
## 2026-03-29
- 修复各塔防设备逻辑，取消使用实体，以避免与其他模组的兼容性问题；经验正常掉落；
- Fixed the logic of each tower defense equipment, canceling the use of entities to avoid compatibility issues with other mods; experience drops normally;
- 暂时移除了部分物品；
- Temporarily removed some items;

## 2026-03-24
- 修改多输入工业设备的逻辑，现在同一个物品只能输入一个物品槽中，当该槽满了以后，不再会再向其他槽输入物品；
- Change the logic of multi-input industrial devices. Now, the same item can only be input into one item slot, and if the slot is full, it will no longer input items into other slots

## 2026-03-23
- 修改塔防设备逻辑，确保塔防设备击杀的怪物也能正常掉落经验；但注意，这是引入了一个不可见的玩家实体，所有伤害源来自于此实体，其名称为`[Turret]`
- Change the logic of tower defense equipment, so that the monsters killed by the tower defense equipment can also drop experience; But note that this is an invisible player entity, all damage sources come from this entity, and its name is `[Turret]`

## 2026-03-20
- 继续完善部分细节，调整一些数值
- Continued to improve some details and adjust some values

## 2026-03-19
- 基本完成另外两端的移植
- Basically completed the porting of the other two ends

## 2026-03-18
- 初步完成各塔防设备的逻辑
- Preliminary completion of the logic for each tower defense equipment

## 2026-03-17
- 初步完成文档站相关内容编写
- Completed writing and deployment of documentation site related to the mod

## 2026-03-15
- 发现一个bug，但予以保留
- Found a bug, but retained
- 修改部分逻辑，以契合此`特性`
- Modified the logic, which is compatible with this feature

## 2026-03-14
- 初步部署模组相关的文档站，并部署一个分站，用于缓解大陆地区访问问题
- Initially deployed the documentation site related to the mod, and deployed a sub-site to alleviate access issues in mainland China

## 2026-03-12
- 测试各炮台
- Tested various gun towers
- 开始编写和部署模组相关的文档站
- Started writing and deploying the documentation site related to the mod

## 2026-03-10
- 各用电工业设备现在采用全局电网节点系统判断与`供电桩`和`中继器`的连接关系，不再直接遍历
- Each powered industrial device now uses a global power grid node system to determine its connection relationship with `Electric Pylon` and `Relay Tower`, instead of directly traversing them
- 修改`协议核心`的发电量，现在发电量为200 EFU/t
- Modified the power generation of `Protocol Anchor Core`, now it generates 200 EFU/t
- 现在工业设备内置电力储备的充能速度为`该设备的耗电量 * 20`，即如果设备的耗电量为10 EFU/t，那么它的充能速度就是200 EFU/s
- Now the charging speed of the built-in power reserve of industrial equipment is `the power consumption of the device * 20`, which means if the device's power consumption is 10 EFU/t, then its charging speed is 200 EFU/s

## 2026-03-09
- 完成矿机分级，且便携源石矿机只能开采源石矿、红石矿和煤矿
- Complete mining machine classification, and portable source stone mining machines can only originium mines, redstone mines, and coal mines
- 现在右键`中继器`或者`供电桩`就可以获取当前电网的相关信息
- Now can get current grid information through right-clicking `Relay Tower` or `Electric Pylon`

## 2026-03-08
- 修改一些逻辑，现在新玩家首次进入游戏会送一些相应的矿脉方块，以后更新都是如此
- Change some logic, now new players will receive some ore vein blocks when they enter the game

## 2026-03-06
- 修改工业设备放置逻辑，如果空间不足，则不会放置
- Modified the logic of industrial equipment placement. If there is not enough space, it will not be placed

## 2026-03-03
- 终末地相关的矿石将采用结构生成，不再直接生成在世界中
- The Endfield's ore stones will be generated using structure generation, rather than being generated in the world
- 修复`传送带`在服务端中放置异常且报错的问题
- Fixed the exception and error when placing `Belt` on the server

## 2026-03-01
- 添加了`物流桥`、`分流器`和`汇流器`
- Added `Belt Bridge`, `Splitter` and `Converger`

## 2026-02-28
- 修改了传送带的逻辑，现在工业设备不会再向推送方向不一致的传送带推送东西了
- e.g. 比如设备向南推送东西，但传送带是东西向的，那么设备就不会向它推送东西
- Modified the logic of conveyor belts. Now, industrial equipment will no longer push items onto conveyor belts that are not aligned with the pushing direction. For example, if a device pushes items south but the conveyor belt is oriented east-west, the device will not push items onto it.
- e.g. If a device pushes items south but the conveyor belt is oriented east-west, the device will not push items onto it.

## 2026-02-25
- 修改传送带的放置逻辑，优化并列摆放
- Modified the placement logic of conveyor belts to optimize side-by-side placement

## 2026-02-23
- 完善传送带
- Improved conveyor belts

## 2026-02-21
- 仿照原版的铁轨，制作了可以直角转弯的传送带
- Created conveyor belts that can turn at right angles, similar to vanilla rails

## 2026-02-20
- 修复`便携源石矿机`相关逻辑
- Fixed logic related to `Portable Originium Rig`

## 2026-02-18
- 优化各个矿机，提取基类
- Optimized various mining rigs and extracted base classes

## 2026-02-17
- 供电桩、中继器现在可以实现断点重连，即当它连接的前一个节点被移除了，那么它会自己重新寻找在连接范围内且最近的节点进行连接
- Electric Pylons and Relay Towers can now implement breakpoint reconnection. If the previous node they are connected to is removed, they will automatically search for the nearest node within the connection range to connect to.

## 2026-02-16
- 优化工业设备，现在用电工业设备可以储备`10000 EFU(Endfield Units)`的电力，增加一定的容错，避免电力不足导致的设备停机
- Optimized industrial equipment. Now, powered industrial equipment can store `10000 EFU (Endfield Units)` of electricity, providing some fault tolerance to prevent equipment shutdown due to insufficient power
- 工业设备如果电力储备充足且不处于工作状态时，不会再消耗电力
- When industrial equipment has sufficient power reserves and is not in working state, it will no longer consume electricity

## 2026-02-11
- 完成各炮台的白模搭建，但尚未加入模组中
- Completed the white model construction of each gun tower, but has not yet been added to the mod

## 2026-02-08
- 新增`全局电网节点管理器`，用于处理供电桩、中继器之间的自动连接
- Added `Global Power Network Node Manager` to handle automatic connections between Electric Pylons and Relay Towers

## 2026-02-07
- 测试验证炮台，优化转向效果
- Tested and verified the gun tower, optimized the turning effect

## 2026-02-06
- `放置预览`试验阶段完成，开始正式编写
- The `placement preview` testing phase is complete, and formal writing begins
- 完成各`GUI`的简化，基类提取完成
- Completed simplification of various `GUIs`, base class extraction completed

## 2026-02-05
- 开始优化各`GUI`，并提取基类
- Started optimizing various `GUIs` and extracting base classes

## 2026-02-04
- 完成`工业设备`方块实体类的优化，基类提取完成
- Completed optimization of `industrial equipment` block entity classes, base class extraction completed

## 2026-02-03
- 优化各`GUI`，降低渲染负载
- Optimized various `GUIs` to reduce rendering load

## 2026-01-12
- 开始整理重构现有的`工业设备相关类`，提取基类，减少重复代码
- Started organizing and refactoring existing `industrial equipment-related classes`, extracting base classes to reduce code duplication

## 2026-01-11
- 尝试使用`Transfer API`来处理各个方块实体中的物品传输逻辑，不再直接使用原版的类
- Attempted to use `Transfer API` to handle item transfer logic in various block entities, no longer directly using vanilla classes

## 2025-12-24
- 整合并测试之前的管道
- Integrated and tested previous pipelines

## 2025-12-16
- 初步试验管道
- Initial testing of pipelines

## 2025-12-15
- 初步试验传送带
- Initial testing of conveyor belts

## 2025-12-14
- 继续重构并优化电网，工业设备将直接接入全局电网，由全局电网负责所有电力运算逻辑
- Continued to refactor and optimize the power grid. Industrial equipment will be directly connected to the global power grid, which is responsible for all power calculation logic.

## 2025-12-12
- 所有测试版停止更新，发布模组未来开发计划，参见[未来计划](https://beishanair.github.io/BessonNote/2025/10/01/endfield/)
- All beta versions have stopped updating. The future development plan of the mod is released. See [Future Plans](https://beishanair.github.io/BessonNote/2025/10/01/endfield/)

## 2025-12-10
- 重构电网，新增一个物品用于模拟电力运输
- Refactored the power grid and added a new item to simulate power transmission

## 2025-12-08
- 继续优化供电桩逻辑
- Further optimized the logic of Electric Pylon

## 2025-11-19
- 优化供电桩逻辑，现在供电桩只会在放置时，只进行一次搜索，搜索半径30格范围内的`协议核心`或`中继器`，如果找到，则会把顶端的连接线渲染出来
- Optimized the logic of Electric Pylon. Now, the Electric Pylon will only perform a one-time search upon placement, searching for `Protocol Anchor Core` or `Relay Tower` within a 30-block radius. If found, it will render the top connection line.

## 2025-11-18
- 进一步优化`协议核心`的性能，移除了方块本身的tick方法，全部交给全局电网管理系统处理
- Further optimized the performance of `Protocol Anchor Core` by removing the block's own tick method and delegating all processing to the Global Power Grid Management System.

## 2025-11-09
- 修正配方问题
- Fixed recipe issues

## 2025-11-03 -> 11-06
- 测试验证`供货终端`
- Tested and verified `Supply Terminal`
- 测试验证`炮塔`
- Tested and verified `Gun Tower`

## 2025-11-02
- 调整了矿物生成的概率
- Adjusted the probability of mineral generation
- 牛、绵羊、猪可以掉落`兽肉`，骷髅和凋零骷髅可以掉落`异香石块`等系列方块
- Cows, sheep, and pigs can drop `Fillet`, while skeletons and wither skeletons can drop `Aggagrit Blocks` and related blocks

## 2025-11-01
- 优化`协议核心端口`，可以实现物品筛选
- Optimized `Protocol Anchor Core Port` to support item filtering
- 添加了一些配方
- Added some recipes

## 2025-10-29
- 调整`供电桩`供电逻辑
- Adjusted the power supply logic of `Electric Pylon`

## 2025-10-28
- 优化`协议核心`tick方法，提高游戏性能
- Optimized the tick method of `Protocol Anchor Core` to improve game performance
- 添加`协议核心端口`，用于输入物品
- Added `Protocol Anchor Core Port` for item input

## 2025-10-27
- 为`协议核心`添加中央仓库，用于存储物品
- Added a central warehouse to `Protocol Anchor Core` for item storage

## 2025-10-25 -> 2025-10-26
- 添加`成就`
- Added `Achievements`

## 2025-10-24
- 修复`制造台`刷物品的特性（bug）
- Fixed the item duplication bug in `Crafter`

## 2025-10-21
- 开始研究流体管道
- Started researching fluid pipelines

## 2025-10-19
- 更新食物
- Updated food items
- `工业爆炸物`可以投掷，落地爆炸
- `Industrial Explosives` can be thrown and will explode upon landing

## 2025-10-18
- 更新`协议核心`GUI界面
- Updated `Protocol Anchor Core` GUI interface

## 2025-10-17
- `制造台`现在关闭GUI时，会自动返回输入槽中未使用的物品
- When closing the GUI of `Crafter`, unused items in the input slots will be automatically returned

## 2025-10-16
- 添加`全局电网管理系统`，统一调配全世界范围内的电力
- Added `Global Power Grid Management System` to centrally manage power distribution worldwide

## 2025-10-15
- 临时追加`晶体外壳`的熔炉和高炉配方，以保证游戏正常推进
- Temporarily added furnace and blast furnace recipes for `Origocrust` to ensure normal game progression
- 追加`热能池`配方
- Added recipe for `Thermal Bank`

## 2025-10-13
- 调整各个工业设备的生产时间
- Adjusted production times for various industrial devices

## 2025-10-12
- 修正各类配方的序列化器，以保证多人游戏正常运行
- Fixed serializers for various recipes to ensure proper operation in multiplayer games

## 2025-10-10
- 修正`制造台`合成时扣除物品数量异常的问题
- Fixed the issue of abnormal item quantity deduction when crafting with `Crafter`

## 2025-10-07
- `Forge`版本开始移植
- The `Forge` version begins to be ported
- 修改命名空间，以减少移植工作量
- Modified the namespace to reduce the amount of porting work

## 2025-10-06
- `REI`适配，支持配方查询
- REI adaptation, supporting recipe lookup

## 2025-10-04
- 修改一些物品的排序
- Modified the sorting of some items

## 2025-10-03
- 增加各种植物的世界生成
- Added world generation for various plants
- 增加`制造台`方块实体，用于合成工业设备
- Added `Crafter` block entity for crafting industrial equipment

## 2025-09-29
- 完成剩余工业设备适配`机械动力`
- Completed adaptation of remaining industrial equipment to `Create`
- 配置矿物的世界生成
- Configured world generation for minerals

## 2025-09-28
- 适配`机械动力`
- Adapted to `Create`
- 现在玩家第一次进入一个新存档将获得一个`协议核心`
- Now players will receive a `Protocol Anchor Core` when they enter a new save for the first time

## 2025-09-20 -> 2025-09-27
- 更新一些物品贴图，方块贴图
- Updated some item textures, block textures

## 2025-09-19
- 添加一堆物品
- Added a bunch of items

## 2025-09-18
- 配置`研磨机`、`封装机`、`灌装机`处理逻辑
- Configured processing logic for `Grinding Unit`, `Packaging Unit`, `Filling Unit`

## 2025-09-17
- 配置`电驱矿机`、`二型电驱矿机`处理逻辑
- Configured processing logic for `Electric Mining Rig` and `Electric Mining Rig Mk II`
- 配置`粉碎机`、`配件机`、`塑形机`、`种植机`、`采种机`处理逻辑
- Configured processing logic for `Shredding Unit`, `Fitting Unit`, `Moulding Unit`, `Planting Unit`, `Seed-Picking Unit`

## 2025-09-16
- 修改`装备原件机`的配方编解码器，支持传入输入物品数量
- Modified the recipe codec of `Gearing Unit` to support input item quantity

## 2025-09-15
- 配置`装备原件机`处理逻辑，实现多输入单输出功能
- Configured processing logic for `Gearing Unit`, implementing multi-input single-output functionality

## 2025-09-14
- 配置`中继器`、`供电桩`、`精炼炉`、`热能池`运行逻辑
- Configured operation logic for `Relay Tower`, `Electric Pylon`, `Refining Unit`, `Thermal Bank`

## 2025-09-13
- 为`协议核心`配置GUI、各个电网参数
- Configured GUI and various power grid parameters for `Protocol Anchor Core`

## 2025-09-11
- 为各个矿石添加对应的矿脉方块，供采矿机采集
- Added ore vein blocks for each ore type, to be collected by mining rigs
- 实装`便携源石矿机`实现逻辑，并配置相应的配方
- Implemented logic for `Portable Originium Rig` and configured corresponding recipes

## 2025-09-10
- 配置`灌装机`、`配件机`、`装备原件机`、`研磨机`、`塑形机`、`封装机`、`种植机`、`采种机`、`热能池`高模
- Configured high-poly model for `Filling Unit`, `Fitting Unit`, `Gearing Unit`, `Grinding Unit`, `Moulding Unit`, `Packaging Unit`, `Planting Unit`, `Seed-Picking Unit`, `Thermal Bank`

## 2025-09-08
- 配置`中继器`、`供电桩`、`电驱矿机`、`便携源石矿机`、`二型电驱矿机`、`精炼炉`、`粉碎机`高模
- Configured high-poly model for `Relay Tower`, `Electric Pylon`, `Electric Mining Rig`, `Portable Originium Rig`, `Electric Mining Rig Mk II`, `Refining Unit`, `Shredding Unit`
- 修正`协议核心`的模型和动画
- Fixed model and animation of `Protocol Anchor Core`

## 2025-09-06
- 配置`协议核心`的高模
- Configured high-poly model for `Protocol Anchor Core`

## 2025-08-27
- 测试`供电桩`供能输出，连接`中继站`或`协议核心`，远程输电
- 测试最简单的一个`用电终端`，由`供电桩`供能，消耗上游`协议核心`的电力
- Tested power output of `Power Supply Pile`, connecting to `Relay Station` or `Protocol Anchor Core` for remote power transmission
- Tested the simplest `Power Consumer`, powered by `Power Supply Pile`, consuming electricity from upstream `Protocol Anchor Core`

## 2025-08-26
- 尝试解决`中继站`和`协议核心`、其他`中继站`之间电线（激光）的渲染问题，但失败了，暂时搁置
- Attempted to solve the rendering issue of wires (lasers) between `Relay Station` and `Protocol Anchor Core` or other `Relay Stations`, but failed and temporarily shelved it

## 2025-08-25
- 测试`中继站`，实现传输`协议核心`或者是最近的`中继站`的电力
- Tested `Relay Station`, which transmits power from the `Protocol Anchor Core` or the nearest `Relay Station`

## 2025-08-24
- 为`热能池`配置GUI，热能池需要燃烧燃料来发电
- 更新`协议核心`的GUI，显示实时数据
- Configured GUI for `Thermal Pool`, which requires burning fuel to generate power
- Updated GUI for `Protocol Anchor Core` to display real-time data

## 2025-08-23
- 尝试构建电网系统，测试`协议核心`自发电功能
- 测试`热能池`的发电功能，并接入`协议核心`，提供额外发电
- Attempted to build a power grid system and tested the self-power generation function of `Protocol Anchor Core`
- Tested the power generation function of `Thermal Pool` and connected it to `Protocol Anchor Core` to provide additional power generation

## 2025-08-22
- 测试多输入多输出口的方块实体，并验证其与`机械动力`的兼容性
- Tested block entities with multiple input and output ports, and verified their compatibility with `Create`

## 2025-08-21
- 测试方块实体输入输出，实现`SideInventory`接口
- Tested block entity input and output, implemented `SideInventory` interface

## 2025-08-20
- 测试`便携源石矿机`可行性
- Tested the feasibility of `Portable Originium Rig`

## 2025-08-19
- `终末地工业`项目正式启动
- `Endfield Industry` project officially launched