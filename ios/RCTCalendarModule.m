// RCTCalendarModule.m
#import "RCTCalendarModule.h"
#import <React/RCTLog.h>

@implementation RCTCalendarModule
{
  bool hasListeners;
}

// Will be called when this module's first listener is added.
-(void)startObserving {
    hasListeners = YES;
    // Set up any upstream listeners or background tasks as necessary
}

// Will be called when this module's last listener is removed, or on dealloc.
-(void)stopObserving {
    hasListeners = NO;
    // Remove upstream listeners, stop unnecessary background tasks
}

- (void)calendarEventReminderReceived {
  if (hasListeners) {// Only send events if anyone is listening
    [self sendEventWithName:@"EventCount" body:@1];
  }
}

- (NSArray<NSString *> *)supportedEvents {
  return @[@"EventCount"];
}

// To export a module named RCTCalendarModule
RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(createCalendarEvent:(NSString *)name location:(NSString *)location myCallback:(RCTResponseSenderBlock)callback)
{
  NSInteger eventId = 1;
  callback(@[@(eventId)]);
  
  RCTLogInfo(@"Pretending to create an event %@ at %@", name, location);
}

RCT_EXPORT_METHOD(createCalendarEventPromise:(NSString *)name
                  location:(NSString *)location
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
  NSInteger eventId = 1;
   if (eventId) {
      resolve(@(eventId));
     [self calendarEventReminderReceived];
    } else {
      reject(@"event_failure", @"no event id returned", nil);
    }
}

@end

