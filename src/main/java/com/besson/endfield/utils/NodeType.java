package com.besson.endfield.utils;

public enum NodeType {
    CORE,
    RELAY {
        @Override
        public boolean canConnectTo(NodeType other) {
            return other == CORE || other == RELAY || other == PYLON;
        }
    },
    PYLON {
        @Override
        public boolean canConnectTo(NodeType other) {
            return other == CORE || other == RELAY || other == PYLON;
        }
    },
    CONSUMER {
        @Override
        public boolean canConnectTo(NodeType other) {
            return other == RELAY || other == PYLON;
        }
    };

    public boolean canConnectTo(NodeType other) {
        return false;
    }
}
