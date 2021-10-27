package com.example.smartsock;

    public interface BLEControllerListener {
        public void BLEControllerConnected();
        public void BLEControllerDisconnected();
        public void BLEDeviceFound(String name, String address);
    }

