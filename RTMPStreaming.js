'use strict'

import React, { Component, PropTypes } from 'react';
import { requireNativeComponent, View } from 'react-native';

class RTMPStreaming extends Component {
  constructor(props, context) {
    super(props, context);
    this._onReady = this._onReady.bind(this);
    this._onConnected = this._onConnected.bind(this);
    this._onDisconnected = this._onDisconnected.bind(this);
    this._onAuthorized = this._onAuthorized.bind(this);
    this._onUnauthorized = this._onUnauthorized.bind(this);
    this._onStreaming = this._onStreaming.bind(this);
    this._onStop = this._onStop.bind(this);
    this._onIOError = this._onIOError.bind(this);
  }

  _onReady(event) {
    this.props.onReady && this.props.onReady(event.nativeEvent);
  }

  _onConnected(event) {
    this.props._onConnected && this.props._onConnected(event.nativeEvent);
  }

  _onDisconnected(event) {
    this.props._onDisconnected && this.props._onDisconnected(event.nativeEvent);
  }

  _onAuthorized(event) {
    this.props._onAuthorized && this.props._onAuthorized(event.nativeEvent);
  }

  _onUnauthorized(event) {
    this.props._onAuthorized && this.props._onUnauthorized(event.nativeEvent);
  }

  _onStreaming(event) {
    this.props.onStreaming && this.props.onStreaming(event.nativeEvent);
  }

  _onStop(event) {
    this.props._onStop && this.props._onStop(event.nativeEvent);
  }

  _onIOError(event) {
    this.props.onIOError && this.props.onIOError(event.nativeEvent);
  }

  render() {
    const nativeProps = Object.assign({}, this.props);
    Object.assign(nativeProps, {
      onReady: this._onReady,
      onConnected: this._onConnected,
      onDisconnected: this._onDisconnected,
      onAuthorized: this._onAuthorized,
      onUnauthorized: this._onUnauthorized,
      onStreaming: this._onStreaming,
      onStop: this.onStop,
      onIOError: this._onIOError,
    });

    return (
      <RCTStreaming
        {...nativeProps}
      />
    )
  }
}

RTMPStreaming.propTypes = {
  rtmpURL: PropTypes.string,
  started: PropTypes.bool,
  stopped: PropTypes.bool,
  onReady: PropTypes.func,
  onConnected: PropTypes.func,
  onDisconnected: PropTypes.func,
  onAuthorized: PropTypes.func,
  onUnauthorized: PropTypes.func,
  onStreaming: PropTypes.func,
  onStop: PropTypes.func,
  onIOError: PropTypes.func,
  ...View.propTypes,
}

const RTMP = requireNativeComponent('RTMP', RTMPStreaming);

module.exports = RTMPStreaming;
