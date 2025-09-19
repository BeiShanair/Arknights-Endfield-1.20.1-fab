# 更新日志 / Changelog

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