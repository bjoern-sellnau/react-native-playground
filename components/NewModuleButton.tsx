import React, {useEffect} from 'react';

import {NativeModules, Button, View, NativeEventEmitter} from 'react-native';
const {CalendarModule, CalendarModuleOther} = NativeModules;

const eventEmitter = new NativeEventEmitter(CalendarModule);
const otherEventEmitter = new NativeEventEmitter(CalendarModuleOther);

const NewModuleButtonIos = () => {
  console.log('CalendarModule:', CalendarModule);
  console.log('CalendarModuleOther:', CalendarModuleOther);

  useEffect(() => {
    eventEmitter.addListener('EventCount', eventCount =>
      console.log(`Events ${eventCount}`),
    );

    return () => {
      eventEmitter.removeAllListeners('EventCount');
    };
  }, []);

  useEffect(() => {
    otherEventEmitter.addListener('EventCountOther', eventCount =>
      console.log(`other Events ${eventCount}`),
    );

    return () => {
      otherEventEmitter.removeAllListeners('EventCountOther');
    };
  }, []);

  const onPress = () => {
    console.log('We will invoke the native module here!');
    CalendarModule.createCalendarEvent('testName', 'testLocation', (res: any) =>
      console.log(`event number ${res} created !`),
    );
    CalendarModuleOther.createCalendarEvent(
      'testName',
      'testLocation',
      (res: any) => console.log(`event number ${res} created (other)!`),
    );
  };

  const createCalendarEventPromise = async () => {
    try {
      const result = await CalendarModule.createCalendarEventPromise(
        'testName',
        'testLocation',
      );
      console.log('Promise ' + result);
    } catch (e) {
      console.log(e);
    }
  };

  const createCalendarOtherEventPromise = async () => {
    try {
      const result = await CalendarModuleOther.createCalendarEventPromise(
        'testName',
        'testLocation',
      );
      console.log('Promise other ' + result);
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <View>
      <Button
        title="Click to invoke your native module! with a callback"
        color="#841584"
        onPress={onPress}
      />
      <Button
        title="Click to invoke your native module! with a promise (ObjectiveC/Java)"
        color="#841584"
        onPress={createCalendarEventPromise}
      />
      <Button
        title="Click to invoke your native module! with a promise (Swift/Kotlin)"
        color="#841584"
        onPress={createCalendarOtherEventPromise}
      />
    </View>
  );
};

export default NewModuleButtonIos;
